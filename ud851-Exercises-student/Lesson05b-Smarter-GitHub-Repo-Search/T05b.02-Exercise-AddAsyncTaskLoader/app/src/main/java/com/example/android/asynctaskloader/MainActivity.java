/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.asynctaskloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.asynctaskloader.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

// TODO (COMPLETED) implement LoaderManager.LoaderCallbacks<String> on MainActivity
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    /* A constant to save and restore the URL that is being displayed */
    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    // TODO (COMPLETED) Remove the key for storing the search results JSON


    // TODO (COMPLETED) Create a constant int to uniquely identify your loader. Call it GITHUB_SEARCH_LOADER
    private static final int GITHUB_SEARCH_LOADER = 22;
    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        if (savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA);

            // TODO (COMPLETED) Remove the code that retrieves the JSON


            mUrlDisplayTextView.setText(queryUrl);
            // TODO (COMPLETED) Remove the code that displays the JSON

        }

        // TODO (COMPLETED) Initialize the loader with GITHUB_SEARCH_LOADER as the ID, null for the bundle, and this for the callback

        getSupportLoaderManager().initLoader(GITHUB_SEARCH_LOADER, null, this);

    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally request that an AsyncTaskLoader performs the GET request.
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();

        // TODO (COMPLETED) If no search was entered, indicate that there isn't anything to search for and return

        if (TextUtils.isEmpty(githubQuery)) {
            mUrlDisplayTextView.setText("No query entered, nothing to search for.");
            return;
        }

        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        // TODO (COMPLETED) Remove the call to execute the AsyncTask

        // TODO (COMPLETED) Create a bundle called queryBundle
        // TODO (COMPLETED) Use putString with SEARCH_QUERY_URL_EXTRA as the key and the String value of the URL as the value

        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, githubSearchUrl.toString());


        // TODO (COMPLETED) Call getSupportLoaderManager and store it in a LoaderManager variable
        // TODO (COMPLETED) Get our Loader by calling getLoader and passing the ID we specified
        // TODO (COMPLETED) If the Loader was null, initialize it. Else, restart it.

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> githubSearchLoader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
        if (githubSearchLoader == null) {
            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        }

    }

    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the JSON data is visible */
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }


    // TODO (COMPLETED) Override onCreateLoader
    // Within onCreateLoader
    // TODO (COMPLETED) Return a new AsyncTaskLoader<String> as an anonymous inner class with this as the constructor's parameter
    // TODO (COMPLETED) Override onStartLoading
    // Within onStartLoading

    // TODO (COMPLETED) If args is null, return.

    // TODO (COMPLETED) Show the loading indicator

    // TODO (COMPLETED) Force a load
    // END - onStartLoading


    // TODO (COMPLETED) Override loadInBackground

    // Within loadInBackground
    // TODO (COMPLETED) Get the String for our URL from the bundle passed to onCreateLoader

    // TODO (COMPLETED) If the URL is null or empty, return null

    // TODO (COMPLETED) Copy the try / catch block from the AsyncTask's doInBackground method
    // END - loadInBackground

    // Yeni bir loader ın başlatılması & oluşturulması ve arkaplan görevinin tetiklenmesi.
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {

        return new AsyncTaskLoader<String>(this) {
            @Override
            public String loadInBackground() {

                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);
                if (TextUtils.isEmpty(searchQueryUrlString)) {
                    return null;
                }
                try {
                    URL githubUrl = new URL(searchQueryUrlString);
                    String githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubUrl);
                    return githubSearchResults;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (args == null)
                    return;
                mLoadingIndicator.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };

    }


    // TODO (COMPLETED) Override onLoadFinished

    // Within onLoadFinished
    // TODO (COMPLETED) Hide the loading indicator

    // TODO (COMPLETED) Use the same logic used in onPostExecute to show the data or the error message
    // END - onLoadFinished

    // TODO (COMPLETED) Override onLoaderReset as it is part of the interface we implement, but don't do anything in this method


    // TODO (COMPLETED) Delete the AsyncTask class


    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mLoadingIndicator.setVisibility(View.GONE);
        if (null == data) {
            showErrorMessage();
        } else {
            mSearchResultsTextView.setText(data);
            showJsonDataView();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO (COMPLETED) Remove the code that persists the JSON
        String rawJsonSearchResults = mSearchResultsTextView.getText().toString();
        outState.putString(SEARCH_QUERY_URL_EXTRA, rawJsonSearchResults);
    }
}