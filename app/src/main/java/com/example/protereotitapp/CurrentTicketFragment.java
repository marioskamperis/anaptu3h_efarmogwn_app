package com.example.protereotitapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentTicketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTicketFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NUMBER = "number";
    private static final String ESTIMATED_TIME= "estimated_tim";
    private static final String SERVICE= "service";
    private static final String ADDRESS= "address";
    private static final String UNIQUE_CODE= "unique_code";

    // TODO: Rename and change types of parameters
    private String number;
    private String estimated_time;
    private String service;
    private String address;
    private String unique_code;

    private OnFragmentInteractionListener mListener;

    private SessionManager session=null;

    public CurrentTicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CurrentTicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentTicketFragment newInstance( String number , String estimated_time, String service , String address , String unique_code) {
        CurrentTicketFragment fragment = new CurrentTicketFragment();
        Bundle args = new Bundle();
        args.putString(NUMBER, number);
        args.putString(ESTIMATED_TIME, estimated_time);
        args.putString(SERVICE, service);
        args.putString(ADDRESS, address);
        args.putString(UNIQUE_CODE, unique_code);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getString(NUMBER);
            estimated_time = getArguments().getString(ESTIMATED_TIME);
            service= getArguments().getString(SERVICE);
            address= getArguments().getString(ADDRESS);
            unique_code = getArguments().getString(UNIQUE_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_ticket, container, false);

        TextView tvNumber = (TextView) rootView.findViewById(R.id.tvNumber);
        TextView tvEstimatedTime = (TextView) rootView.findViewById(R.id.tvTime);
        TextView tvService=(TextView) rootView.findViewById(R.id.tvService);
        TextView tvAddress =(TextView) rootView.findViewById(R.id.tvAddress);
        TextView tvUniqueCode =(TextView) rootView.findViewById(R.id.tvUniqueCode);
        TextView tvName =(TextView) rootView.findViewById(R.id.tvName);


        tvNumber.setText(number);
        tvEstimatedTime.setText(estimated_time);
        tvService.setText(service);
        tvAddress.setText(address);
        tvUniqueCode.setText(unique_code);

        //Set Session
        session

        return rootView;
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
}
