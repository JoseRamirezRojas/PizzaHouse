package com.ciencias.tarea2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ciencias.tarea2.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TextView total;
    private Button button;
    //Bundle bundle = this.getArguments();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ((MainActivity)getActivity()).setTitle("Tarea 2");
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        total = (TextView) view.findViewById(R.id.textView12);

        //String value = this.getArguments().getString("message"); //get your parameter
        Log.d("totaaaal", getArguments().getString("message"));
        total.setText(getArguments().getString("message"));
        //Toast.makeText(getActivity(), getArguments().getString("message")+" ", Toast.LENGTH_LONG).show();//show data in tost
        button = (Button) view.findViewById(R.id.button_first2);
        return view;

    }

    // This is a public method that the Activity can use to communicate
    // directly with this Fragment
    public void totalPedido(String monto) {
        binding.textView12.setText(monto);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stars = binding.ratingBar.getNumStars();
                Log.d("calif",Integer.toString(stars));
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}