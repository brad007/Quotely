package com.fire.quotely.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fire.quotely.R;
import com.fire.quotely.etc.FirebaseConstants;
import com.fire.quotely.models.Submission;
import com.fire.quotely.ui.dialogs.SubmissionDialog;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fire.quotely.adapters.TitleAdapter.TitleViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmissionsFragment extends Fragment {

    @BindView(R.id.submissions_recyclerview)
    RecyclerView mSubmissionsRecyclerView;
    private FirestoreRecyclerAdapter<Submission, TitleViewHolder> mAdapter;

    public SubmissionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submissions, container, false);
        ButterKnife.bind(this, view);
        mSubmissionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = FirebaseFirestore.getInstance()
                .collection(FirebaseConstants.SUBMISSIONS)
                .orderBy("title");

        FirestoreRecyclerOptions<Submission> options = new FirestoreRecyclerOptions.Builder<Submission>()
                .setQuery(query, Submission.class)
                .build();

        mAdapter = new
                FirestoreRecyclerAdapter<Submission, TitleViewHolder>(options) {
                    @Override
                    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title,
                                parent, true);
                        return new TitleViewHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(TitleViewHolder holder, int position, Submission model) {
                        holder.setTitle(model.getTitle());
                        holder.setQuote(model.getTagline());
                        holder.setLogo(model.getAcronym());
                    }


                };


        mSubmissionsRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @OnClick(R.id.fab)
    void showSubmissionDialog() {
        SubmissionDialog dialog = new SubmissionDialog();
        dialog.show(getFragmentManager(), null);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null)
            mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null)
            mAdapter.stopListening();
    }
}
