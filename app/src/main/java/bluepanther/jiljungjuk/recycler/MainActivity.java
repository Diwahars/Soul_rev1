package bluepanther.jiljungjuk.recycler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bluepanther.jiljungjuk.R;

public class MainActivity extends AppCompatActivity {

    public static Toolbar toolbar;
    public static boolean is_in_action_mode = false;
    static TextView counter_text;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    static View.OnLongClickListener myOnLongClickListener;
    private static ArrayList<Integer> removedItems;

    static ArrayList<DataModel> selectionList = new ArrayList<>();
    static int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


//        counter_text = (TextView) findViewById(R.id.counter_text);
        counter_text.setVisibility(View.GONE);


        myOnClickListener = new MyOnClickListener(this);
        myOnLongClickListener = new MyOnLongClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.titleArray.length; i++) {
            data.add(new DataModel(
                    MyData.titleArray[i],
                    MyData.categoryArray[i],
                    MyData.uploaderArray[i],
                    MyData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

      //  adapter = new CustomAdapterGT(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

//    @Override
//    public boolean onLongClick(View v){
//        toolbar.getMenu().clear();
//        toolbar.inflateMenu(R.menu.context_menu);
//        counter_text.setVisibility(View.VISIBLE);
//        is_in_action_mode = true;
//        adapter.notifyDataSetChanged();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        return true;
//    }

    private static class MyOnLongClickListener implements View.OnLongClickListener{
        private final Context context;

        private MyOnLongClickListener(Context context) { this.context = context;}

        @Override
        public boolean onLongClick(View v) {
            System.out.println("HOLAAAAAA");
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.context_menu);
            counter_text.setVisibility(View.VISIBLE);
            is_in_action_mode = true;
            adapter.notifyDataSetChanged();
            return true;
        }
    }

    public static void prepareSelection(View view, int position)
    {

        if(((CheckBox)view).isChecked())
        {
            selectionList.add(data.get(position));
            counter = counter+1;
            updateCounter(counter);
        }
        else
        {
            selectionList.remove(data.get(position));
            counter = counter-1;
            updateCounter(counter);
        }

    }

    public static void updateCounter(int counter)
    {
        if(counter==0)
        {
            counter_text.setText("0 item(s) selected");
        }
        else if(counter==1)
        {
            counter_text.setText("1 item selected");
        }
        else
        {
            counter_text.setText(counter+" items selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_share)
        {
            clearActionMode();
        }
        else if(item.getItemId()==R.id.menu_save)
        {
            clearActionMode();
        }
        else if(item.getItemId()==R.id.menu_delete)
        {
            clearActionMode();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearActionMode()
    {
        is_in_action_mode=false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter_text.setVisibility(View.GONE);
        counter_text.setText("0 item(s) selected");
        counter=0;
        selectionList.clear();

    }



    @Override
    public void onBackPressed() {

        if(is_in_action_mode)
        {
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        else {
            super.onBackPressed();
        }
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
           // removeItem(v);

        }

//        private void removeItem(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.title);
//            String selectedName = (String) textViewName.getText();
//            int selectedItemId = -1;
//            for (int i = 0; i < MyData.nameArray.length; i++) {
//                if (selectedName.equals(MyData.nameArray[i])) {
//                    selectedItemId = MyData.id_[i];
//                }
//            }
//            removedItems.add(selectedItemId);
//            data.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
//        }
    }




}