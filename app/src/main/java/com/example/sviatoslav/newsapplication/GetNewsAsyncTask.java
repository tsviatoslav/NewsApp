package com.example.sviatoslav.newsapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Created by Sviatoslav on 12/19/2016.
 */

public class GetNewsAsyncTask extends AsyncTask {

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    String jsonResultStr = null;

    protected interface AsyncResponse {
        void processFinish(String output);
    }

    private AsyncResponse delegate = null;

    GetNewsAsyncTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            URL url = new URL("https://newsapi.org/v1/articles?source=time&sortBy=top&apiKey=613fffffe62c4267800e5744ec61127b");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                Log.d("DEBUG_TAG", line);
                buffer.append(line + '\n');
            }
            if (buffer.length() == 0) {
                return null;
            }
            jsonResultStr = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResultStr;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        delegate.processFinish(jsonResultStr);
    }
}
