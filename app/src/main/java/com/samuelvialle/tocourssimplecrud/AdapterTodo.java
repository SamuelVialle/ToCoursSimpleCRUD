package com.samuelvialle.tocourssimplecrud;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AdapterTodo extends FirestoreRecyclerAdapter<ModelTodo, AdapterTodo.TodoViewModel> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterTodo(@NonNull FirestoreRecyclerOptions<ModelTodo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TodoViewModel holder, int position, @NonNull ModelTodo model) {
        holder.etTitle.setText(model.title);
        holder.etContent.setText(model.content);
    }

    @NonNull
    @Override
    public TodoViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewModel(view);
    }

    public class TodoViewModel extends RecyclerView.ViewHolder {
        private TextView etTitle, etContent;
        private Button btnDelete, btnEdit;
        public TodoViewModel(@NonNull View itemView) {
            super(itemView);
            etTitle = itemView.findViewById(R.id.etTitle);
            etContent = itemView.findViewById(R.id.etContent);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        DocumentSnapshot snapshot = getSnapshots().getSnapshot(position);
                        String state = "Delete";
                        onItemClickListener.onItemClick(snapshot, position, state);
                    }
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        DocumentSnapshot snapshot = getSnapshots().getSnapshot(position);
                        String state = "Edit";
                        onItemClickListener.onItemClick(snapshot, position, state);
                    }
                }
            });


        }
    }

    // Gestion des erreurs grâce à la méthode héritée de FirebaseFirestoreException
    @Override
    public void onError(FirebaseFirestoreException e) {
        // S'il y a des erreurs on les affiche dans le log error
        Log.e("error", e.getMessage());
    }

    /** Interface **/
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position, String state);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
