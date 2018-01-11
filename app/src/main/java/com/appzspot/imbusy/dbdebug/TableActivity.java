package com.appzspot.imbusy.dbdebug;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appzspot.imbusy.R;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbdebug_activity_table);

        Intent intent = getIntent();

        if ( intent != null ){

            if ( intent.getStringExtra("table") != null){

                // start loader and get data
                final Activity activity = this;
                final String tableName = intent.getStringExtra("table");
                final ListView tableView = (ListView) findViewById(R.id.db_debug_tablecontainer);

                getLoaderManager().initLoader(300, null, new LoaderManager.LoaderCallbacks<ArrayList<String>>() {
                    @Override
                    public Loader<ArrayList<String>> onCreateLoader(int i, Bundle bundle) {
                        return new TableDataLoader(activity, tableName);
                    }

                    @Override
                    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> strings) {

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1, strings);
                        tableView.setAdapter(arrayAdapter);


                    }

                    @Override
                    public void onLoaderReset(Loader<ArrayList<String>> loader) {

                    }
                }).forceLoad();

            }


        }

    }


    private static class TableDataLoader extends AsyncTaskLoader<ArrayList<String>>{

        private static String tableName;

        public TableDataLoader(Context context, String tableN) {
            super(context);
            tableName = tableN;
        }

        @Override
        public ArrayList<String> loadInBackground() {
            ArrayList<String> tableData = new ArrayList<String>();

//            Cursor dbCursor = ActiveAndroid.getDatabase().query(tableName, null, null, null, null, null, null);
//
//            String[] columnNames = dbCursor.getColumnNames();
//            int colSize = columnNames.length;
//
//            String rowData = "";
//
//            for ( int i = 0 ; i < colSize ; i++ ){
//
//                if ( i != 0 && i != (colSize -1) )
//                    rowData += " - ";
//
//                rowData += columnNames[i];
//            }
//
//            tableData.add(rowData);
//
//            if (dbCursor.moveToFirst()) {
//                while ( !dbCursor.isAfterLast() ) {
//                    rowData = "";
//
//                    for ( int i = 0; i < colSize ; i++ ){
//
//                        if ( i != 0 && i != (colSize ) )
//                            rowData += " - ";
//
//                        rowData += dbCursor.getString(i);
//
//                    }
//
//                    tableData.add(rowData);
//
//                    dbCursor.moveToNext();
//                }
//            }
//
            return null;
        }
    }

}
