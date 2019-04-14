//Project : Final_Hamilton
//Author: Dylan J. Hamilton
//Date: 12/9/2018
//Class: ItemListActivity
//Descritption: creates activity for the recyclerview of dummyitems/data provided by user



package com.example.jkozlevcar.masterdetailex2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jkozlevcar.masterdetailex2.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements DeleteConfirmDialog.DeleteDialogListener
{


    //Final revisions and additions. First Step.
    //this is the context used for the database.
    Context context = this;

    //creates DB Handler Object
    private DatabaseHandler dbh = new DatabaseHandler(context);

    //Creates cursor reference
    private Cursor cursor;

    //Creates reference for the adapter
    private SimpleItemRecyclerViewAdapter adapter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    //Method Name: onCreate
    //Arguements: Bundle Saved instance
    //Description: creates the item list activity
    //calling methods: Android runtime
    //Methods called: setupRecyclerView
    //Returns: void
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //Floating Action Button Creation in the onCreate method.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });

        //COMMENTING OUT IF STATEMENT To remove mTwoPane logic
        if (findViewById(R.id.item_detail_container) != null)
        {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        //Creates the RecyclerView
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        //Final Revision and Addition
        //Test the Business Table
        DatabaseTest.testBusinessTable(context);

        //Creates the cursor using the Business Table
        cursor = dbh.getAllContactBusiness();

        //Sets up RecyclerView
        setupRecyclerView((RecyclerView) recyclerView);
    }

    //Method Name: setupRecyclerView
    //Arguements: RecyclerView object
    //Description: creates and sets the adpater
    //calling methods: ItemListActivity OnCreate
    //Methods called: SimpleItemRecyclerViewAdapter contructor
    //Returns: void
    private void setupRecyclerView(@NonNull RecyclerView recyclerView)
    {
            //Final Revision and Addition
            //Replaces the list with the cursor
            //Saves the Adapter in reference
            adapter = new SimpleItemRecyclerViewAdapter(this, cursor, mTwoPane);
            recyclerView.setAdapter(adapter);
    }

    //Final Revision
    //Add a new cursor
    public void setCursor(Cursor newCursor){
        cursor = newCursor;
    }

  /*  @Override
    public void addDialogPositiveClick(ContactBusiness cbObject) {
        // add the contact to the database
        dbh.addContactBusiness(cbObject);

        // generate a new cursor
        cursor = dbh.getAllContactBusiness();

        // set in a new cursor. 
        adapter.setCursor(cursor);

        // notify the adapter of the change
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addDialogNegativeClick() {

    }
    */

    @Override
    public void deleteDialogPositiveClick(String name) {
        
        // remove the contact with name from the database
        dbh.deleteContactBusinessByName(name);

        // generate a new cursor
        cursor = dbh.getAllContactBusiness();

        // set in a new cursor.
        adapter.setCursor(cursor);

        // notify the adapter of the change
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteDialogNegativeClick() {

    }

    //Method Name: SimpleItemRecyclerViewAdapter
    //Arguements: ItemListActivity parent - parent activity
    // List<DummyContent.DummyItem> items - list of dummy items
    // boolean twoPane - one pane for phone, 2 for tablet
    //Description: create the adapter
    //calling methods: setupRecyclerView
    //Methods called: none
    //Returns: nothing
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
    {

        //Prent
        private final ItemListActivity mParentActivity;

        //Final Revision and Addition
        //Addition of Cursor in SimpleItemRecyclerViewAdapter class.
        private Cursor cursor;

        //boolean determining if the app is running on 2 panes. false if 3.
        private final boolean mTwoPane;

        //OnClickListener
        private final View.OnClickListener mOnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

                //Final Revision and Addition, add Contact DB data here.
                //Gets the string details from the tag.
                String[] details = (String[])view.getTag();

                if (mTwoPane)
                {
                 //   Bundle arguments = new Bundle();
                  //  arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                  //  ItemDetailFragment fragment = new ItemDetailFragment();
                   // fragment.setArguments(arguments);
                   // mParentActivity.getSupportFragmentManager().beginTransaction()
                   //         .replace(R.id.item_detail_container, fragment)
                   //         .commit();
                } else
                {
                    //Gets the context from the view
                    Context context = view.getContext();
                   //Creates an intent for the detail activity
                    Intent intent = new Intent(context, ItemDetailActivity.class);

                    //Final Revision and Addition
                    //Puts the String Details in the intent instead of item.id
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, details);

                    //Starts the Activity
                    context.startActivity(intent);
                }
            }
        };

        //Constructor Replacements
        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      Cursor items,
                                      boolean twoPane)
        {
            //Final Revision and Addition
           //Calls Cursor Items
            cursor = items;
            //Parent Activity
            mParentActivity = parent;

            //True for Tablet View
            mTwoPane = twoPane;
        }


        //Method Name: onCreateViewHolder
        //Arguements: ViewGroup parent = parent which is layout
        //Description: creates a viewholder object and inflates its layout
        //calling methods: adapter
        //Methods called: none
        //Returns: viewholder object
        //Final: Do Nothing Here. Changes in the tag on the onClick method.
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }


        //Method Name: onBindViewHolder
        //Arguements: final ViewHolder holder, int position
        //Description: binds data to textViews
        //calling methods: adapter
        //Methods called: none
        //Returns: void
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position)
        {
            //Final Revision and Additions
            //Move the cursor into position
            cursor.moveToPosition(position);

            //Assign the id from the cursor to the id view
            holder.mIdView.setText(cursor.getString(0));

            //get the same name from the cursor and assign it to context
            holder.mContentView.setText(cursor.getString(1));

            //Final Revision and Addition
            //Create String [] Details from the cursor columns
            String[] details = {cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8),};


            holder.itemView.setTag(details);
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount()
        {
            //Final Revision and Addition
            return cursor.getCount();
        }

        public void setCursor(Cursor cursor) {
            this.cursor = cursor;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view)
            {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
