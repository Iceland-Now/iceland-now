package is.ru.google_maps_test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FilterDialog extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private FilterDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Filter Map")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener()  {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = editTextUsername.getText().toString();
                        String password = editTextPassword.getText().toString();
                        listener.applyTexts(username, password);
                    }
                });
        editTextUsername = view.findViewById(R.id.edit_username);
        editTextPassword = view.findViewById(R.id.edit_password);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (FilterDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement FilterDialogListener");
        }
    }

    public interface FilterDialogListener {
        void applyTexts(String username, String password);
    }
}
