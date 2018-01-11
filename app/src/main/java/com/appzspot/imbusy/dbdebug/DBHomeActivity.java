package com.appzspot.imbusy.dbdebug;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.appzspot.imbusy.R;
import com.orm.SugarApp;
import com.orm.SugarContext;

import java.util.ArrayList;


public class DBHomeActivity extends AppCompatActivity {

   private String[] options = { "View Design" , "View Data" };

   @Override
   protected void onCreate ( Bundle savedInstanceState ) {
      super.onCreate ( savedInstanceState );
      setContentView ( R.layout.dbdebug_activity_dbhome );


      TextView dbName = ( TextView ) findViewById ( R.id.db_debug_dbname );
      final ListView tablesListView = ( ListView ) findViewById ( R.id.db_debug_dbtables );

      final Activity thisActivity = this;
      getLoaderManager ().initLoader ( 200, null, new LoaderManager.LoaderCallbacks < ArrayList < String > > () {
         @Override
         public Loader < ArrayList < String > > onCreateLoader ( int i, Bundle bundle ) {
            return new GetAllTables ( thisActivity );
         }

         @Override
         public void onLoadFinished ( Loader < ArrayList < String > > loader, ArrayList < String > strings ) {
            ArrayAdapter < String > itemsAdapter =
                    new ArrayAdapter < String > ( thisActivity, android.R.layout.simple_list_item_1, strings );

            tablesListView.setAdapter ( itemsAdapter );

            tablesListView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
               @Override
               public void onItemClick ( AdapterView < ? > adapterView, View view, int i, long l ) {

                  final String tableName = ( String ) adapterView.getItemAtPosition ( i );

                  AlertDialog.Builder alertDialogBuilder =
                          new AlertDialog.Builder ( thisActivity );
                  alertDialogBuilder.setTitle ( "TABLE : " + tableName );

                  alertDialogBuilder.setItems ( options,
                          new DialogInterface.OnClickListener () {

                             @Override
                             public void onClick ( DialogInterface dialog, int which ) {

                                //rest of your implementation
                                // View table data
                                if ( which == 1 ) {

                                   Intent intent = new Intent ( thisActivity, TableActivity.class );
                                   intent.putExtra ( "table", tableName );
                                   thisActivity.startActivity ( intent );

                                } else {

                                   thisActivity.getLoaderManager ().initLoader ( 900, null, new LoaderManager.LoaderCallbacks < String > () {
                                      @Override
                                      public Loader < String > onCreateLoader ( int i, Bundle bundle ) {
                                         return new GetTableSchema ( thisActivity, tableName );
                                      }

                                      @Override
                                      public void onLoadFinished ( Loader < String > loader, String s ) {

                                         final AlertDialog.Builder builder = new AlertDialog.Builder ( thisActivity );
                                         builder.setTitle ( tableName + " :Schema " );
                                         builder.setPositiveButton ( "OK", new DialogInterface.OnClickListener () {
                                            @Override
                                            public void onClick ( DialogInterface dialogInterface, int i ) {
                                               dialogInterface.dismiss ();
                                            }
                                         } );
                                         builder.setMessage ( s );
                                         builder.create ().show ();
                                      }

                                      @Override
                                      public void onLoaderReset ( Loader < String > loader ) {

                                      }
                                   } ).forceLoad ();

                                }


                             }
                          } );

                  alertDialogBuilder.create ().show ();

               }
            } );
         }

         @Override
         public void onLoaderReset ( Loader < ArrayList < String > > loader ) {

         }
      } ).forceLoad ();

   }

   private static class GetTableSchema extends AsyncTaskLoader < String > {

      private static String tableName;

      public GetTableSchema ( Context context, String tName ) {
         super ( context );
         tableName = tName;
      }

      @Override
      public String loadInBackground () {

//         String schema = "";
//
//         //Cursor c = ActiveAndroid.getDatabase ().rawQuery ( "PRAGMA table_info([" + tableName + "])", null );
//         SugarContext.getSugarContext ().getSugarDb();
//
//         String[] columnNames = c.getColumnNames ();
//         int colSize = columnNames.length;
//
//         String rowData = "";
//
//         for ( int i = 0 ; i < colSize ; i++ ) {
//
//            if ( i != 0 && i != ( colSize - 1 ) )
//               rowData += " - ";
//
//            rowData += columnNames[ i ];
//         }
//
//         schema += rowData + "\n";
//
//         if ( c.moveToFirst () ) {
//            while ( !c.isAfterLast () ) {
//               rowData = "";
//
//               for ( int i = 0 ; i < colSize ; i++ ) {
//
//                  if ( i != 0 && i != ( colSize - 1 ) )
//                     rowData += " - ";
//
//                  rowData += c.getString ( i );
//
//               }
//
//               schema += rowData + "\n";
//
//               c.moveToNext ();
//            }
//         }
//
//         return schema;
         return "";
      }
   }

   private static class GetAllTables extends AsyncTaskLoader < ArrayList < String > > {

      public GetAllTables ( Context context ) {
         super ( context );
      }

      @Override
      public ArrayList < String > loadInBackground () {
//         ArrayList < String > tables = new ArrayList < String > ();
//
//         Cursor c = ActiveAndroid.getDatabase ().rawQuery ( "SELECT name FROM sqlite_master WHERE type='table'", null );
//
//         if ( c.moveToFirst () ) {
//            while ( !c.isAfterLast () ) {
//               tables.add ( c.getString ( 0 ) );
//               c.moveToNext ();
//            }
//         }
//
//
         return null;
      }
   }


}
