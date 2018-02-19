package titulacion.sise.canchala10.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import titulacion.sise.canchala10.R;
import titulacion.sise.canchala10.Remote.Data.SedeResponse;
import titulacion.sise.canchala10.Remote.SOService;
import titulacion.sise.canchala10.Utils.ApiUtils;
import titulacion.sise.canchala10.adaptadores.AdaptadorSedes;
import titulacion.sise.canchala10.entidades.Sede;
import titulacion.sise.canchala10.interfaces.IComunicaFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SedeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SedeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SedeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    List<Sede> sedes;
    RecyclerView recyclerViewSedes;
    SOService soService;
    private GridLayoutManager glm;
    Activity activity;
    IComunicaFragment iComunicaFragment;

    public SedeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SedeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SedeFragment newInstance(String param1, String param2) {
        SedeFragment fragment = new SedeFragment();
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
        View vista = inflater.inflate(R.layout.fragment_sede, container, false);

        sedes = new ArrayList<Sede>();
        recyclerViewSedes = (RecyclerView) vista.findViewById(R.id.recyclerSede);
        recyclerViewSedes.setLayoutManager(new LinearLayoutManager(getContext()));
        soService = ApiUtils.getSOService();

        LlenarSedes();


        return vista;
    }

    private void LlenarSedes() {
        soService.getSedes().enqueue(new Callback<SedeResponse>() {
            @Override
            public void onResponse(Call<SedeResponse> call, Response<SedeResponse> response) {
                if(response.isSuccessful()){
                    SedeResponse sedeResponse = response.body();
                    sedes = sedeResponse.getResponse();

                    AdaptadorSedes adaptadorSedes = new AdaptadorSedes(sedes);
                    /*glm = new GridLayoutManager(getContext(), 2);
                    recyclerViewSedes.setLayoutManager(glm);*/

                    recyclerViewSedes.setAdapter(adaptadorSedes);

                    adaptadorSedes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            iComunicaFragment.enviarSede(sedes.get(recyclerViewSedes.getChildAdapterPosition(view)));
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<SedeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

        if(context instanceof Activity){
            this.activity =(Activity) context;
            iComunicaFragment =(IComunicaFragment) this.activity;
        }

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
}
