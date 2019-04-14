//Project :  Final_Hamilton
//Author:    Dylan J.Hamilton
//Date:      12/9/2018
//Class: ItemDetailFragment
//Descritption: creates a fragment to display item details after an item has been selected


package com.example.jkozlevcar.masterdetailex2;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jkozlevcar.masterdetailex2.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    //Final Revision and Addition
    //String Details this fragment is representing
    private String[] details;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment()
    {
    }

    //Method Name: onCreate
    //Arguements: Bundle savedInstanceState - previous state
    //Description: called to create the fragment before onCreateView
    //calling methods: Java runtime
    //Methods called: none
    //Returns: void
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID))
        {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            details = getArguments().getStringArray(ARG_ITEM_ID);

            Activity activity = this.getActivity();

            //Sets Content on the ToolBar
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                //Final Revision and Addition
                //Switch content on the toolbar from Dummy Content to DB Content
                appBarLayout.setTitle(details[0]);
            }
        }
    }


    //Method Name: onCreateView
    //Arguements: Layout inflater - inflater
    //ViewGroup container - parent container
    //Bundle savedInstanceState - previous state
    //Description: create the fragment after onCreate
    //calling methods: Java runtime
    //Methods called: none
    //Returns: void
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (details != null)
        // Final Revision and Addition
        {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(
                            details[1] + "/n" +
                            details[2] + "/n" +
                            details[3] + "/n" +
                            details[4] + "/n" +
                            details[5] + "/n" +
                            details[6] + "/n" +
                            details[7] + "/n");
        }

        return rootView;
    }
}
