package com.ciencias.tarea2;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.ciencias.tarea2.databinding.FragmentFirstBinding;
import com.ciencias.tarea2.databinding.FragmentSecondBinding;

public class FirstFragment extends Fragment {

    //private OnFirstFragmentListener mCallback;
    private FragmentFirstBinding binding;
    private ThemedNumberPicker picker1,picker2,picker3,picker4,picker5,picker6;
    private Button button;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ((MainActivity)getActivity()).setTitle("Tarea 2");
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        picker1 = (ThemedNumberPicker)view.findViewById(R.id.numberpicker_1);
        picker1.setMaxValue(8);
        picker1.setMinValue(0);
        picker2 = (ThemedNumberPicker)view.findViewById(R.id.numberpicker_2);
        picker2.setMaxValue(10);
        picker2.setMinValue(0);
        picker3 = (ThemedNumberPicker)view.findViewById(R.id.numberpicker_3);
        picker3.setMaxValue(8);
        picker3.setMinValue(0);
        picker4 = (ThemedNumberPicker)view.findViewById(R.id.numberpicker_4);
        picker4.setMaxValue(10);
        picker4.setMinValue(0);
        picker5 = (ThemedNumberPicker)view.findViewById(R.id.numberpicker_5);
        picker5.setMaxValue(8);
        picker5.setMinValue(0);
        picker6 = (ThemedNumberPicker)view.findViewById(R.id.numberpicker_6);
        picker6.setMaxValue(10);
        picker6.setMinValue(0);
        button = (Button) view.findViewById(R.id.button_first);
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int montoCompra = (picker1.getValue() * 15) + (picker2.getValue() * 90) +
                                  (picker3.getValue() * 15) + (picker4.getValue() * 90) +
                                  (picker5.getValue() * 15) + (picker6.getValue() * 90);
                Log.d("monto compra",Integer.toString(montoCompra));
                button.setVisibility(View.GONE);
                Bundle bundle = new Bundle();
                bundle.putString("message", Integer.toString(montoCompra)); // set your parameteres
                SecondFragment nextFragment = new SecondFragment();
                nextFragment.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.constraint1 , nextFragment).
                        setTransition(TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();

                Log.d("monto compra",Integer.toString(montoCompra));
                //NavHostFragment.findNavController(FirstFragment.this)
                  //      .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }

}