package com.example.lab2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonShowData.setOnClickListener(view -> showData());
        binding.buttonGenerateColor.setOnClickListener(view -> generateBackgroundColor());
        binding.radioGroupTextColor.setOnCheckedChangeListener(this::changeLabelColor);
    }

    private void showData() {
        String category = getCategory();
        String message = getString(
                R.string.data_message,
                binding.editTextName.getText().toString().trim(),
                binding.editTextSurname.getText().toString().trim(),
                binding.editTextAge.getText().toString().trim(),
                category
        );
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private String getCategory() {
        int checkedId = binding.radioGroupCategory.getCheckedRadioButtonId();
        if (checkedId == R.id.radioCategoryFirst) {
            return getString(R.string.category_first);
        }
        if (checkedId == R.id.radioCategorySecond) {
            return getString(R.string.category_second);
        }
        return getString(R.string.no_category);
    }

    private void generateBackgroundColor() {
        String red = binding.checkBoxRed.isChecked() ? "FF" : "00";
        String green = binding.checkBoxGreen.isChecked() ? "FF" : "00";
        String blue = binding.checkBoxBlue.isChecked() ? "FF" : "00";

        if (!binding.checkBoxRed.isChecked()
                && !binding.checkBoxGreen.isChecked()
                && !binding.checkBoxBlue.isChecked()) {
            Toast.makeText(this, R.string.no_color, Toast.LENGTH_SHORT).show();
            return;
        }

        binding.main.setBackgroundColor(Color.parseColor("#" + red + green + blue));
    }

    private void changeLabelColor(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radioTextRed) {
            binding.textViewColorLabel.setTextColor(getColor(R.color.label_red));
        } else if (checkedId == R.id.radioTextGreen) {
            binding.textViewColorLabel.setTextColor(getColor(R.color.label_green));
        } else if (checkedId == R.id.radioTextBlue) {
            binding.textViewColorLabel.setTextColor(getColor(R.color.label_blue));
        }
    }
}
