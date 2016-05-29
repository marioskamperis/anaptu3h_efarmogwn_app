package com.example.protereotitapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, PlaceSelectionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "BookFragment";
    private TextView mPlaceDetailsText;

    private static FloatingActionButton fab;
    private static View mProgressView;

    private TextView mPlaceAttribution;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleApiClient mGoogleApiClient;


    private OnFragmentInteractionListener mListener;

    public BookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(getActivity())
                .build();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_book, container, false);

        mPlaceDetailsText = (TextView) rootView.findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) rootView.findViewById(R.id.place_attribution);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fabBookTicket);



        //progress bar
        mProgressView = rootView.findViewById(R.id.book_progress);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);


        //LatLong Bounds
        LatLngBounds bounds = new LatLngBounds(new LatLng(37.58, 23.43), new LatLng(37.58, 23.43));

        //Filter
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
//                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                .setTypeFilter(1013)
//                .setTypeFilter(AutocompleteFilter.)
                .build();
        autocompleteFragment.setBoundsBias(bounds);
//        autocompleteFragment.setFilter(typeFilter);


        try {
            autocompleteFragment.setOnPlaceSelectedListener(this);
        } catch (Exception e) {
            Log.d(TAG, "Exception :" + e);
            Toast.makeText(getActivity().getApplicationContext(), "Exception caught!", Toast.LENGTH_SHORT).show();
        }


        return rootView;
//        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    public void onConnectionFailed(ConnectionResult result) {
        Log.d("BookFragment", "On connection failed Google APi client");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();

    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPlaceSelected(Place place) {

        Log.i(TAG, "Place Selected: " + place);

        // Format the returned place's details and display them in the TextView.
        mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri(), place.getPlaceTypes(), place.getLatLng()));

        CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
            mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        } else {
            mPlaceAttribution.setText("");
        }

        showProgress(true);

        checkPlace(place);

    }

    public void onError(Status status) {
//        Log.e(TAG, "onError: Status = " + status.toString());

        Toast.makeText(getActivity().getApplicationContext(), "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to format information about a place nicely.
     */
    @SuppressLint("StringFormatMatches")
    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri, List<Integer> placeTypes, LatLng latLng) {
//        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,websiteUri, placeTypes, latLng));

        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri, placeTypes, latLng));

    }

    /**
     * function to verify login details in mysql db
     */
    private void checkPlace(final Place place) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_CHECK_PLACE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "ticket response: " + response);

                showProgress(false);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    fab.setVisibility(View.INVISIBLE);
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        //TODO make android session
//                        session.setLogin(true);

                        // Now store the user in SQLite
//                        String uid = jObj.getString("uid");


                        fab.setVisibility(View.VISIBLE);
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bookTicket(place);
                            }
                        });

                        // Inserting row in users table
//                        db.addUser(name, email, uid, created_at);
                        String errorMsg = String.valueOf(jObj.get("msg"));
                        Toast.makeText(getActivity().getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = String.valueOf(jObj.get("msg"));
                        Toast.makeText(getActivity().getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Response Error : " + errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d(TAG, "Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                try {
                    params.put("google_place_id", place.getId());
                    params.put("name", String.valueOf(place.getName()));
                    params.put("address", String.valueOf(place.getAddress()));
                    params.put("lat", String.valueOf(place.getLatLng().latitude));
                    params.put("lon", String.valueOf(place.getLatLng().longitude));
                    params.put("telephone", String.valueOf(place.getPhoneNumber()));
                    params.put("type", place.getPlaceTypes().toString());
                    params.put("attributes", String.valueOf(place.getAttributions()));
                    params.put("website", place.getWebsiteUri().toString());
                    //TODO request actual user id
                    params.put("user_id", "1234");
                } catch (Exception e) {
                    Log.d(TAG, "Exception at :" + e.getStackTrace());
                }
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
    private void bookTicket(final Place place) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_BOOK_TICKET, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "book ticket response: " + response);

                showProgress(false);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    fab.setVisibility(View.INVISIBLE);
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        //TODO make android session
//                        session.setLogin(true);

                        // Now store the user in SQLite
//                        String uid = jObj.getString("uid");



                        // Inserting row in users table
//                        db.addUser(name, email, uid, created_at);
                        String estimated_time = String.valueOf(jObj.get("estimated_time"));
                        String average_time = String.valueOf(jObj.get("average_time"));
                        String number = String.valueOf(jObj.get("number"));
                        String unique_code = String.valueOf(jObj.get("unique_code"));
                        String expiration_date = String.valueOf(jObj.get("expiration_date"));

                        Toast.makeText(getActivity().getApplicationContext(), "Estimated time: "+estimated_time+ " Number:"+number, Toast.LENGTH_LONG).show();



                    } else {
                        // Error in login. Get the error message
                        String errorMsg = String.valueOf(jObj.get("msg"));
                        Toast.makeText(getActivity().getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Response Error : " + errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d(TAG, "Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                try {
                    params.put("google_place_id", place.getId());
//                    params.put("name", String.valueOf(place.getName()));
//                    params.put("address", String.valueOf(place.getAddress()));
//                    params.put("lat", String.valueOf(place.getLatLng().latitude));
//                    params.put("lon", String.valueOf(place.getLatLng().longitude));
//                    params.put("telephone", String.valueOf(place.getPhoneNumber()));
//                    params.put("type", place.getPlaceTypes().toString());
//                    params.put("attributes", String.valueOf(place.getAttributions()));
//                    params.put("website", place.getWebsiteUri().toString());
//                    //TODO request actual user id
                    params.put("user_id", "1234");
                } catch (Exception e) {
                    Log.d(TAG, "Exception at :" + e.getStackTrace());
                }
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

}
