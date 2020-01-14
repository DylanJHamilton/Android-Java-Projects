//Item List Activity Class
//Basically the Mainframe of the Master/Detail Flow
//Ties in all Data with the RecyclerView passing from the Database Classes and Methods that Apply to the Program / App.

package com.call43studio.jeepswapshop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.call43studio.jeepswapshop.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private Context context = this;

    // create the database handler object
    private DatabaseHandler dbh = new DatabaseHandler(context);

    // create the cursor reference
    private Cursor cursor;

    // add reference for adapter
    private SimpleItemRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);


        // test the jeep testing
        DatabaseTest.testListingTable(context);

        // create the cursor using the business table
        cursor = dbh.getAllListingJeep();

        setupRecyclerView((RecyclerView) recyclerView);

    }




    private void setupRecyclerView(@NonNull RecyclerView recyclerView)
    {
        // replace List with Cursor
        // save adapter in reference
        adapter = new SimpleItemRecyclerViewAdapter(this, cursor, mTwoPane);
        recyclerView.setAdapter(adapter);
    }
    // RecyclerView's adapter
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
    {

        // the parent
        private final ItemListActivity mParentActivity;

        // replace List mValues with cursor
        private Cursor cursor;

        // boolean determining if app is running on 2 panes, false is 1 pane
        private final boolean mTwoPane;

        // onClick listener
        private final View.OnClickListener mOnClickListener = new View.OnClickListener()
        {
            // Method name:     onClick
            // Arguments:       the view causing the click event
            // description:     handles click event for the adapter, calls detail activity
            // Calling methods: Android runtime
            // Methods called:
            // Returns:         void
            @Override
            public void onClick(View view)
            {
                // tag contains dummyItem, set in onBindView
//                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();


                // get the String[] details from the tag
                String[] details = (String[])view.getTag();

                if (mTwoPane)
                {
//                    // if mTwoPane is true, load fragment for tablets
//                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
//                    ItemDetailFragment fragment = new ItemDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit();
                } else
                {
                    // get the context from the view
                    Context context = view.getContext();

                    // can also cast as activity
                    ItemListActivity activity = (ItemListActivity)view.getContext();

                    // create an intent for the detail activity
                    Intent intent = new Intent(context, ItemDetailActivity.class);

                    // Final revision
                    // put the String[] details in the intent instead of the item.id
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, details);

                    // start the activity
                    context.startActivity(intent);
                }
            }
        };
        private DatabaseHandler dbh;


        // setCursor Method
        public void setCursor(Cursor newCursor)
        {
            cursor = newCursor;
        }

        // SimpleItemRecyclerViewAdapter constructor

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      Cursor items,
                                      boolean twoPane)
        {
            cursor = items;

            // parent actvity
            mParentActivity = parent;

            // true for tablet and 2 panes
            mTwoPane = twoPane;
        }

        //Insert Dialog
        @Override
        public void addDialogPositiveClick(ListingJeep cbObject)
        {
            // add the contact to the database
            dbh.addListingJeep(cbObject);

            // generate a new cursor
            cursor = dbh.getAllListingJeep();

            // set in a new cursor.
            adapter.setCursor(cursor);

            // notify the adapter of the change
            adapter.notifyDataSetChanged();
        }
        
        

        //onCreateViewHolder
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

       //onBindViewHolder
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // move the cursor to the position
            cursor.moveToPosition(position);

            // assign the id from the cursor to the id view
            holder.mIdView.setText(cursor.getString(0));

            // get the name from the cursor and assign it to content
            holder.mContentView.setText(cursor.getString(1));

            // set the tag to the dummyItem
            // itemView is inherited from ViewHolder, root view or layout
            // Unlike IDs, tags are not used to identify views.
            // Tags are essentially an extra piece of information that can be associated with a view.
            // They are most often used as a convenience to store data related to views in
            // the views themselves rather than by putting them in a separate structure.
//            holder.itemView.setTag(mValues.get(position));

            // Final revision
            // create String[] details from the cursor columns
            String[] details = {cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8)};

            // put the String[] details in the tag
            holder.itemView.setTag(details);

            // set the onClick listener to the item
            holder.itemView.setOnClickListener(mOnClickListener);

            // longClick added for delete
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    // get the String[] details from the tag
                    String[] details = (String[])v.getTag();

                    System.out.println("onLongClick: " + details[0]);

                    // create a bundle object
                    Bundle args = new Bundle();

                    // add the name selected to the bundle with key name
                    args.putString("name", details[0]);

                    // get the activity from the view
                    ItemListActivity activity = (ItemListActivity)v.getContext();

                    // true means event was handled
                    return true;
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return cursor.getCount();
        }

        // this class represents a viewHolder, used to make one item in the recyclerView
        class ViewHolder extends RecyclerView.ViewHolder
        {
            final TextView mIdView;
            final TextView mContentView;

            // constructor
            // view is the itemView
            ViewHolder(View view)
            {
                super(view);

                // create Java objects for textViews in the viewHolder
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
