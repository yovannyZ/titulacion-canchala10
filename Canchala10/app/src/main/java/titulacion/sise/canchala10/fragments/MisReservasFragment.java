package titulacion.sise.canchala10.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.Remote.Data.ReservaResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.adaptadores.AdaptadorMisReservas;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MisReservasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MisReservasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisReservasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SOService soService;
    private FirebaseAuth mAuth;
    RecyclerView rvMisreservas;

    public MisReservasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisReservasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisReservasFragment newInstance(String param1, String param2) {
        MisReservasFragment fragment = new MisReservasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_mis_reservas, container, false);

        rvMisreservas = (RecyclerView) vista.findViewById(R.id.rvMisReservas);
        rvMisreservas.setLayoutManager(new LinearLayoutManager(getContext()));
        soService = ApiUtils.getSOService();
        mAuth = FirebaseAuth.getInstance();
        obtenerReserva();
        return vista;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void obtenerReserva(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        soService.getReservasByUsuario(currentUser.getEmail()).enqueue(new Callback<ReservaResponse>() {
            @Override
            public void onResponse(Call<ReservaResponse> call, Response<ReservaResponse> response) {
                if(response.isSuccessful()){
                    ReservaResponse reservaResponse = response.body();
                    if(reservaResponse.getStatus()){
                        AdaptadorMisReservas adaptadorMisReservas = new AdaptadorMisReservas(reservaResponse.getReserva());
                        rvMisreservas.setAdapter(adaptadorMisReservas);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReservaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
