//package bluepanther.jiljungjuk;
//
//import android.app.*;
//import android.app.Notification;
//import android.content.ContentValues;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.provider.OpenableColumns;
//import android.support.design.widget.NavigationView;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnPausedListener;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.luseen.spacenavigation.SpaceItem;
//import com.luseen.spacenavigation.SpaceNavigationView;
//import com.luseen.spacenavigation.SpaceOnClickListener;
//import com.luseen.spacenavigation.SpaceOnLongClickListener;
//import com.sa90.materialarcmenu.ArcMenu;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import bluepanther.jiljungjuk.Academics_Grid.MainActivity;
//import bluepanther.jiljungjuk.Reports_Grid.Reports;
//
//
//public class NewTabActivity extends AppCompatActivity {
//
//    /**
//     * The {@link android.support.v4.view.PagerAdapter} that will provide
//     * fragments for each of the sections. We use a
//     * {@link FragmentPagerAdapter} derivative, which will keep every
//     * loaded fragment in memory. If this becomes too memory intensive, it
//     * may be best to switch to a
//     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
//     */
//    private SectionsPagerAdapter mSectionsPagerAdapter;
//
//    /**
//     * The {@link ViewPager} that will host the section contents.
//     */
//    private ViewPager mViewPager;
//    ArcMenu arcMenu, arcMenu2, arcMenu3;
//    ImageView imageView;
//    Uri imageUri;
//    Uri selectedImageUri;
//    DrawerLayout mDrawerLayout;
//    NavigationView mNavigationView;
//    FragmentManager mFragmentManager;
//    FragmentTransaction mFragmentTransaction;
//
//    private static final int PICK_IMAGE = 1;
//    private static final int PICK_Camera_IMAGE = 2;
//
//    protected static final int REQUEST_PICK_VIDEO = 3;
//    private static final int VIDEO_CAPTURE = 4;
//
//    private static final int PICK_FILE = 5;
//
//    protected static final int REQUEST_PICK_AUDIO = 6;
//    public static final int ACTIVITY_RECORD_SOUND = 7;
//
//    Uri VideoUri;
//    String path,idesc,adesc,vdesc,fdesc;
//    Uri audioUri;
//    Uri fileUri = null;
//    String date,desc;
//    String fname;
//    TextDesc textDesc;
//    ImageDesc imageDesc;
//    Audiodesc audiodesc;
//    VideoDesc videoDesc;
//    FileDesc fileDesc;
//
//    String descr,textcontent;
//    Dialog text_dialog,image_dialog,audio_dialog,video_dialog,file_dialog;
//
//    Notification notification,notification2,notification3,notification4;
//
//    double progress = 0.0,progress2=0.0,progress3=0.0,progress4 = 0.0;
//
//    private String Base_url = "https://soul-for-schools.firebaseio.com/";
//    private Firebase fb_db;
//    private Firebase fb_db2;
//    private SpaceNavigationView spaceNavigationView;
//    ImageDesc img;
//    ArrayList<String> targetmems;
//    TextDesc txt;
//    String tar;
//    Audiodesc aud;
//    ArrayList<String>tempmems;
//    VideoDesc vid;
//    FileDesc fil;
//    ProgressBar progressBar;
//    List<CharSequence> list = new ArrayList<CharSequence>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_tab);
//CurrentUser.user="subash";
//        Category.maincat="Test1";
//        Category.subcat="Test2";
//        CurrentUser.sclass="4";
//        CurrentUser.ssec="A";
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//        Firebase.setAndroidContext(this);
//        fb_db=new Firebase(Base_url);
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//
//        arcMenu = (ArcMenu) findViewById(R.id.arcMenu);
//        arcMenu2 = (ArcMenu) findViewById(R.id.arcMenu2);
//        arcMenu3 = (ArcMenu) findViewById(R.id.arcMenu3);
//        arcMenu.setRadius(500);
//        arcMenu2.setRadius(300);
//        arcMenu3.setRadius(300);
//
//
//        findViewById(R.id.fagroup).setOnClickListener(subMenuClickListener6);
//        findViewById(R.id.faindi).setOnClickListener(subMenuClickListener7);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;
//
//        /**
//         * Lets inflate the very first fragment
//         * Here , we are inflating the TabFragment as the first Fragment
//         */
//
//        mFragmentManager = getSupportFragmentManager();
//        mFragmentTransaction = mFragmentManager.beginTransaction();
////        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
//        /**
//         * Setup click events on the Navigation View Items.
//         */
//
////        if (mNavigationView != null) {
////            mDrawerLayout = (DrawerLayout) mNavigationView.getChildAt(0);
////            if (mDrawerLayout != null) {
////                mDrawerLayout.setVerticalScrollBarEnabled(false);
////            }
////        }
//
//
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//                mDrawerLayout.closeDrawers();
//
//
//                if (menuItem.getItemId() == R.id.nav_reports) {
//
//                    Intent intent = new Intent(NewTabActivity.this, Reports.class);
//                    startActivity(intent);
//
//                    Toast.makeText(NewTabActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_friends) {
//
////                    Intent intent = new Intent(NewTabActivity.this, Contacts.class);
////                    startActivity(intent);
//
//                    Toast.makeText(NewTabActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_myprofile) {
//
////                    Intent intent = new Intent(NewTabActivity.this, MyProfile.class);
////                    startActivity(intent);
//
//                    Toast.makeText(NewTabActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_settings) {
//
//
//                    Toast.makeText(NewTabActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_logout) {
//
//
//                    Toast.makeText(NewTabActivity.this, "" + menuItem, Toast.LENGTH_SHORT).show();
//
//                }
//                return false;
//            }
//        });
//
//        /**
//         * Setup Drawer Toggle of the Toolbar
//         */
//
//
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
//        R.string.app_name);
//
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        mDrawerToggle.syncState();
//
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(getBaseContext(),"Selected tab is "+tab.toString(),Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        findViewById(R.id.fab1).setOnClickListener(subMenuClickListener1);
//        findViewById(R.id.fab2).setOnClickListener(subMenuClickListener2);
//        findViewById(R.id.fab3).setOnClickListener(subMenuClickListener3);
//        findViewById(R.id.fab4).setOnClickListener(subMenuClickListener4);
//        findViewById(R.id.fab5).setOnClickListener(subMenuClickListener5);
//
//
//    }
//    private View.OnClickListener subMenuClickListener6 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            tar="GROUP";
//            arcMenu2.toggleMenu();
//
//
//        }
//    };
//    private View.OnClickListener subMenuClickListener7 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            tar="INDIVIDUAL";
//            arcMenu2.toggleMenu();
//
//        }
//    };
//    private View.OnClickListener subMenuClickListener1 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            selectText();
//
//        }
//    };
//    private View.OnClickListener subMenuClickListener2 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            selectImage();
//
//        }
//    };
//    private View.OnClickListener subMenuClickListener3 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            selectAudio();
//
//        }
//    };
//
//    private View.OnClickListener subMenuClickListener4 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            selectVideo();
//
//        }
//    };
//    private View.OnClickListener subMenuClickListener5 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            selectFiles();
//
//        }
//    };
//
//    private void selectText()
//    {
//        text_dialog = new Dialog(this);
//        text_dialog.setContentView(R.layout.text_new);
//        text_dialog.setTitle("Share To Group");
//
//        final EditText editText = (EditText) text_dialog.findViewById(R.id.editText);
//        final EditText desc = (EditText) text_dialog.findViewById(R.id.desc);
//        Button upload = (Button) text_dialog.findViewById(R.id.upload);
//
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textcontent=editText.getText().toString();
//                descr=desc.getText().toString();
//                 new MyTaskt().execute();
//                text_dialog.dismiss();
//            }
//        });
//        // Make dialog box visible.
//        text_dialog.show();
//    }
//
//    private void selectImage() {
//        final CharSequence[] items = { "Take Photo", "Choose from Library",
//                "Cancel" };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(NewTabActivity.this);
//        builder.setTitle("Choose picture..");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (items[item].equals("Take Photo")) {
//                    String fileName = "new-photo-name.jpg";
//                    //create parameters for Intent with filename
//                    ContentValues values = new ContentValues();
//                    values.put(MediaStore.Images.Media.TITLE, fileName);
//                    values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
//                    //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
//                    imageUri = NewTabActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                    //create new Intent
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//                    startActivityForResult(intent, PICK_Camera_IMAGE);
//                } else if (items[item].equals("Choose from Library")) {
//                    Intent intent = new Intent(
//                            Intent.ACTION_PICK,
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(
//                            Intent.createChooser(intent, "Select File"),
//                            PICK_IMAGE);
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//
//
//    }
//
//    private void selectAudio()
//    {
//        final CharSequence[] items = { "Record Audio", "Choose from Library",
//                "Cancel" };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(NewTabActivity.this);
//        builder.setTitle("Choose audio..");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (items[item].equals("Record Audio")) {
//
//                    Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//                    startActivityForResult(intent, ACTIVITY_RECORD_SOUND);
//                } else if (items[item].equals("Choose from Library")) {
//                    Intent intent = new Intent();
//                    intent.setType("audio/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(Intent.createChooser(intent,"Select Audio "), REQUEST_PICK_AUDIO);
//
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//
//
//    }
//
//    private void selectVideo() {
//        final CharSequence[] items = { "Take Video", "Choose from Library",
//                "Cancel" };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(NewTabActivity.this);
//        builder.setTitle("Choose video..");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (items[item].equals("Take Video")) {
//                    File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
//                    int cnt=f.listFiles().length;
//                    System.out.println("FIle cnt is "+cnt+"and they are"+f.listFiles());
//                    File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
//                            + "/myvideo"+Integer.toString(cnt+1)+".mp4");
//                    path=Environment.getExternalStorageDirectory().getAbsolutePath()+ "/myvideo"+Integer.toString(cnt+1)+".mp4";
//                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                    VideoUri = Uri.fromFile(mediaFile);
//
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, VideoUri);
//                    startActivityForResult(intent, VIDEO_CAPTURE);
//
//
//                } else if (items[item].equals("Choose from Library")) {
//                    Intent gintent = new Intent();
//                    gintent.setType("video/*");
//                    gintent.setAction(Intent.ACTION_PICK);
//                    startActivityForResult(
//                            Intent.createChooser(gintent, "Select Picture"),
//                            REQUEST_PICK_VIDEO);
//
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//
//
//    }
//
//    private void selectFiles()
//    {
//        Intent gintent = new Intent();
//        gintent.setType("file/*");
//        gintent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(
//                Intent.createChooser(gintent, "Select File"),
//                PICK_FILE);
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//
//            if (requestCode == PICK_Camera_IMAGE) {
//                if (resultCode == NewTabActivity.this.RESULT_OK) {
//                    //use imageUri here to access the image
//                    selectedImageUri = imageUri;
//                } else if (resultCode == NewTabActivity.this.RESULT_CANCELED) {
//                    Toast.makeText(NewTabActivity.this, "Picture was not taken", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(NewTabActivity.this, "Picture was not taken", Toast.LENGTH_SHORT).show();
//                }
//                image_dialog = new Dialog(this);
//                image_dialog.setContentView(R.layout.image_new);
//                image_dialog.setTitle("Share To Group");
//
//                imageView = (ImageView) image_dialog.findViewById(R.id.imageView);
//                final EditText desc = (EditText) image_dialog.findViewById(R.id.desc);
//                Button upload = (Button) image_dialog.findViewById(R.id.upload);
//
//                upload.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        idesc = desc.getText().toString();
//                        new MyTaski().execute();
//                        image_dialog.dismiss();
//                        Toast.makeText(NewTabActivity.this,
//                                "Image Shared", Toast.LENGTH_LONG).show();
//                    }
//                });
//                // Make dialog box visible.
//                image_dialog.show();
//            } else if (requestCode == PICK_IMAGE) {
//                if (resultCode == RESULT_OK) {
//                    selectedImageUri = data.getData();
//                } else if (resultCode == NewTabActivity.this.RESULT_CANCELED) {
//                    Toast.makeText(NewTabActivity.this, "Picture was not selected", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(NewTabActivity.this, "Picture was not selected", Toast.LENGTH_SHORT).show();
//                }
//                image_dialog = new Dialog(this);
//                image_dialog.setContentView(R.layout.image_new);
//                image_dialog.setTitle("Share To Group");
//
//                imageView = (ImageView) image_dialog.findViewById(R.id.imageView);
//                final EditText desc = (EditText) image_dialog.findViewById(R.id.desc);
//                Button upload = (Button) image_dialog.findViewById(R.id.upload);
//
//                upload.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        idesc = desc.getText().toString();
//                        new MyTaski().execute();
//                        image_dialog.dismiss();
//                        Toast.makeText(NewTabActivity.this,
//                                "Image Shared", Toast.LENGTH_LONG).show();
//                    }
//                });
//                // Make dialog box visible.
//                image_dialog.show();
//            }
//
//
//        if (requestCode == REQUEST_PICK_VIDEO) {
//
//
//            if (data.equals(null)) {
//                Toast.makeText(NewTabActivity.this, "Video was not selected", Toast.LENGTH_SHORT).show();
//            } else {
//                VideoUri = data.getData();
//                Toast.makeText(NewTabActivity.this, VideoUri.toString(), Toast.LENGTH_SHORT).show();
//                Filepath obj = new Filepath();
//                path = obj.getPaths(NewTabActivity.this, VideoUri);
//                System.out.println("VIDEOO" + VideoUri + " d " + VideoUri.getPath());
//                video_dialog = new Dialog(this);
//                video_dialog.setContentView(R.layout.video_new);
//                video_dialog.setTitle("Share To Group");
//
//                VideoView videoView = (VideoView) video_dialog.findViewById(R.id.videoView);
//                final EditText desc = (EditText) video_dialog.findViewById(R.id.desc);
//                Button upload = (Button) video_dialog.findViewById(R.id.upload);
//
//                upload.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        vdesc = desc.getText().toString();
//                        new MyTaskv().execute();
//                        video_dialog.dismiss();
//                        Toast.makeText(NewTabActivity.this,
//                                "Image Shared", Toast.LENGTH_LONG).show();
//                    }
//                });
//                // Make dialog box visible.
//                video_dialog.show();
//            }
//        }
//
//        if (requestCode == VIDEO_CAPTURE) {
//            if (resultCode == NewTabActivity.this.RESULT_OK) {
//                Toast.makeText(NewTabActivity.this, "Video has been saved to:\n" +
//                        data.getData(), Toast.LENGTH_LONG).show();
//            } else if (resultCode == NewTabActivity.this.RESULT_CANCELED) {
//                Toast.makeText(NewTabActivity.this, "Video recording cancelled.",
//                        Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(NewTabActivity.this, "Failed to record video",
//                        Toast.LENGTH_LONG).show();
//            }
//            video_dialog = new Dialog(this);
//            video_dialog.setContentView(R.layout.video_new);
//            video_dialog.setTitle("Share To Group");
//
//            VideoView videoView = (VideoView) video_dialog.findViewById(R.id.videoView);
//            final EditText desc = (EditText) video_dialog.findViewById(R.id.desc);
//            Button upload = (Button) video_dialog.findViewById(R.id.upload);
//
//            upload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vdesc = desc.getText().toString();
//                    new MyTaskv().execute();
//                    video_dialog.dismiss();
//                    Toast.makeText(NewTabActivity.this,
//                            "Image Shared", Toast.LENGTH_LONG).show();
//                }
//            });
//            // Make dialog box visible.
//            video_dialog.show();
//        }
//        if (requestCode == REQUEST_PICK_AUDIO) {
//            audioUri = data.getData();
//            //  String path = audioUri.getPath(); // "file:///mnt/sdcard/FileName.mp3"
//
//            Filepath obj = new Filepath();
//            path = obj.getPaths(NewTabActivity.this, audioUri);
//            System.out.println("Path is " + audioUri + " d " + obj.getPaths(NewTabActivity.this, audioUri));
//            audio_dialog = new Dialog(this);
//            audio_dialog.setContentView(R.layout.audio_new);
//            audio_dialog.setTitle("Share To Group");
//
//
//            final EditText desc = (EditText) audio_dialog.findViewById(R.id.desc);
//            Button upload = (Button) audio_dialog.findViewById(R.id.upload);
//
//            upload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    adesc = desc.getText().toString();
//                    new MyTaska().execute();
//                    audio_dialog.dismiss();
//                    Toast.makeText(NewTabActivity.this,
//                            "Image Shared", Toast.LENGTH_LONG).show();
//                }
//            });
//            // Make dialog box visible.
//            audio_dialog.show();
//        }
//        if (requestCode == ACTIVITY_RECORD_SOUND) {
//            audioUri = data.getData();
//            //  String path = audioUri.getPath(); // "file:///mnt/sdcard/FileName.mp3"
//
//            Filepath obj = new Filepath();
//            path = obj.getPaths(NewTabActivity.this, audioUri);
//            System.out.println("Path is " + audioUri + " d " + obj.getPaths(NewTabActivity.this, audioUri));
//            audio_dialog = new Dialog(this);
//            audio_dialog.setContentView(R.layout.audio_new);
//            audio_dialog.setTitle("Share To Group");
//
//
//            final EditText desc = (EditText) audio_dialog.findViewById(R.id.desc);
//            Button upload = (Button) audio_dialog.findViewById(R.id.upload);
//
//            upload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    adesc = desc.getText().toString();
//                    new MyTaska().execute();
//                    audio_dialog.dismiss();
//                    Toast.makeText(NewTabActivity.this,
//                            "Image Shared", Toast.LENGTH_LONG).show();
//                }
//            });
//            // Make dialog box visible.
//            audio_dialog.show();
//        }
//        if (resultCode == PICK_FILE) {
//            fileUri = data.getData();
//            Filepath obj = new Filepath();
//            path = obj.getPaths(NewTabActivity.this, fileUri);
//            System.out.println(fileUri + " d " + fileUri.getPath());
//            file_dialog = new Dialog(this);
//            file_dialog.setContentView(R.layout.file_new);
//            file_dialog.setTitle("Share To Group");
//
//
//            final EditText desc = (EditText) file_dialog.findViewById(R.id.desc);
//            Button upload = (Button) file_dialog.findViewById(R.id.upload);
//
//            upload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    fdesc = desc.getText().toString();
//                    new MyTaskf().execute();
//                    file_dialog.dismiss();
//                    Toast.makeText(NewTabActivity.this,
//                            "Image Shared", Toast.LENGTH_LONG).show();
//                }
//            });
//            // Make dialog box visible.
//            file_dialog.show();
//        }
//    }
//    }
//
//    public String getPath(Uri uri, Activity activity) {
//        String[] projection = { MediaStore.MediaColumns.DATA };
//        Cursor cursor = activity
//                .managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_new_tab, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_new_tab, container, false);
//            ListView listView = (ListView) rootView.findViewById(R.id.listView);
//
//            return rootView;
//        }
//    }
//
//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
//        }
//
//        @Override
//        public int getCount() {
//            // Show 2 total pages.
//            return 2;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "GROUPS";
//                case 1:
//                    return "PERSONAL";
//
//            }
//            return null;
//        }
//    }
//    private class MyTaskt extends AsyncTask<String, Integer, String> {
//
//        // Runs in UI before background thread is called
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //     progressDialog = ProgressDialog.show(getActivity(), "Message", "Uploading Image...");
//
//        }
//
//        // This is run in a background thread
//        @Override
//        protected String doInBackground(String... params) {
//
//            txt = new TextDesc();
//            txt.setUser(CurrentUser.user);
//            txt.setDate(date);
//            txt.setDesc(descr);
//            txt.setMaincat(Category.maincat);
//            txt.setSubcat(Category.subcat);
//            txt.setText(textcontent);
//            txt.setDate(new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()));
//            if(txt.getText().isEmpty())
//            {
//                Toast.makeText(getApplication(),"Enter the Text",Toast.LENGTH_SHORT).show();
//
//            }
//            else if( txt.getDesc().isEmpty())
//            {
//                Toast.makeText(getApplication(),"Enter The description", Toast.LENGTH_SHORT).show();
//
//            }
//            if(tar.equals("GROUP"))
//            {
//                txt.setTarget("all");
//                txt.setTargetmems(new ArrayList<String>());
//            }
//            else if(tar.equals("INDIVIDUAL"))
//            {
//                txt.setTarget("indi");
//                targetmems=new ArrayList(tempmems);
//                txt.setTargetmems(targetmems);
//
//            }
//            fb_db.child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Texts").child(CurrentUser.user+txt.getDate()).setValue(txt);
////                        Intent i = new Intent(HomeFrag.this, HomeFrag.class);
////                        i.putExtra("bool1", false);
////                        startActivity(i);
////                        finish();
//
//
//          //  Toast.makeText(getApplicationContext(),"Text Shared", Toast.LENGTH_LONG).show();
//
//
//            return "SUCCESS";
//        }
//
//
//        // This runs in UI when background thread finishes
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (result.equals("SUCCESS")) {
//                System.out.println("SUCCESS");
//
//            }
//
//
//            //    progressDialog.dismiss();
//            // Do things like hide the progress bar or change a TextView
//        }
//    }
//    private class MyTaski extends AsyncTask<String, Integer, String> {
//
//        // Runs in UI before background thread is called
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //     progressDialog = ProgressDialog.show(getActivity(), "Message", "Uploading Image...");
//
//        }
//
//        // This is run in a background thread
//        @Override
//        protected String doInBackground(String... params) {
//
//
//
//
//            img = new ImageDesc();
//            img.setUser(CurrentUser.user);
//
//            img.setDesc(idesc);
//            img.setMaincat(Category.maincat);
//            img.setSubcat(Category.subcat);
//
//
//                img.setDate(new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()));
//                if (tar.equals("GROUP")) {
//                    img.setTarget("all");
//                    img.setTargetmems(new ArrayList<String>());
//                } else if (tar.equals("INDIVIDUAL")) {
//                    img.setTarget("indi");
//                    ArrayList targetmems = new ArrayList(tempmems);
//                    img.setTargetmems(targetmems);
//
//                }
//                final String imagenode = CurrentUser.user + img.getDate();
//                System.out.println("  imagenode  " + imagenode);
//                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Images").child(imagenode);
//
//                System.out.println("Storage refference : " + storageReference);
//
//                final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                final android.app.Notification.Builder notificationBuilder = new android.app.Notification.Builder(getApplicationContext());
//                notificationBuilder.setOngoing(true)
//                        .setContentTitle("Uploading Image")
//                        .setContentText("Progressed (" + (int) progress + " %)")
//                        .setProgress(100, (int) progress, false)
//
//                        .setSmallIcon(R.drawable.soul_logo);
//                notification = notificationBuilder.build();
//                notificationManager.notify(100, notification);
//
//                UploadTask up = storageReference.putFile(selectedImageUri);
//                up.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                        progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                        System.out.println("Progress is " + progress);
//                        notificationBuilder.setProgress(100, (int) progress, false).setContentText("Progressed (" + (int) progress + "%...)");
//                        notification = notificationBuilder.build();
//                        notificationManager.notify(100, notification);
//
//                    }
//                }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        fb_db.child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Images").child(imagenode).setValue(img);
//
//                        notificationBuilder.setProgress(100, (int) progress, false).setContentText("Progressed (" + (int) progress + "%)").setOngoing(false).setDefaults(android.app.Notification.DEFAULT_ALL);
//                        notification = notificationBuilder.build();
//                        notificationManager.notify(100, notification);
//                        Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_LONG).show();
//                        Uploader.istate=false;
////                                    Intent i2 = new Intent(HomeFrag.this, HomeFrag.class);
////                                    i2.putExtra("bool1", false);
////                                    startActivity(i2);
////                                    finish();
//
//                    }
//                });
//
//
//
//
//            return "SUCCESS";
//        }
//
//
//        // This runs in UI when background thread finishes
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (result.equals("SUCCESS")) {
//                System.out.println("SUCCESS");
//
//            }
//            //    progressDialog.dismiss();
//            // Do things like hide the progress bar or change a TextView
//        }
//
//        public String getFileName(Uri uri) {
//            String result = null;
//            if (uri.getScheme().equals("content")) {
//                Cursor cursor = NewTabActivity.this.getContentResolver().query(uri, null, null, null, null);
//                try {
//                    if (cursor != null && cursor.moveToFirst()) {
//                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                    }
//                } finally {
//                    cursor.close();
//                }
//            }
//            if (result == null) {
//                result = uri.getPath();
//                int cut = result.lastIndexOf('/');
//                if (cut != -1) {
//                    result = result.substring(cut + 1);
//                }
//            }
//            return result;
//        }
//    }
//
//    private class MyTaska extends AsyncTask<String, Integer, String> {
//
//        // Runs in UI before background thread is called
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //     progressDialog = ProgressDialog.show(getActivity(), "Message", "Uploading Image...");
//
//        }
//
//        // This is run in a background thread
//        @Override
//        protected String doInBackground(String... params) {
//
//            aud = new Audiodesc();
//            aud.setUser(CurrentUser.user);
//
//            aud.setDesc(adesc);
//            aud.setMaincat(Category.maincat);
//            aud.setSubcat(Category.subcat);
//
//                aud.setDate(new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()));
//                if (aud.getDesc().isEmpty()) {
//                    Toast.makeText(getApplication(), "Enter the Description", Toast.LENGTH_SHORT).show();
//
//                }
//
//
//                if (tar.equals("GROUP")) {
//                    aud.setTarget("all");
//                    aud.setTargetmems(new ArrayList<String>());
//                } else if (tar.equals("INDIVIDUAL")) {
//                    aud.setTarget("indi");
//                    ArrayList targetmems = new ArrayList(tempmems);
//                    aud.setTargetmems(targetmems);
//
//                }
//                final String audnode = CurrentUser.user + aud.getDate();
//                System.out.println("  audionode  " + audnode);
//                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference().child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Audios").child(audnode);
//
//                System.out.println("Storage refference : " + storageReference2);
//
//                final NotificationManager notificationManager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                final android.app.Notification.Builder notificationBuilder2 = new android.app.Notification.Builder(getApplicationContext());
//                notificationBuilder2.setOngoing(true)
//                        .setContentTitle("Uploading Audio")
//                        .setContentText("Progressed (" + (int) progress2 + " %)")
//                        .setProgress(100, (int) progress2, false)
//                        .setSmallIcon(R.drawable.soul_logo);
//                notification2 = notificationBuilder2.build();
//                notificationManager2.notify(101, notification2);
//
//                UploadTask up1 = storageReference2.putFile(audioUri);
//                up1.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                        progress2 = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                        System.out.println("Progress is " + progress2);
//                        notificationBuilder2.setProgress(100, (int) progress2, false).setContentText("Progressed (" + (int) progress2 + "%...)");
//                        notification2 = notificationBuilder2.build();
//                        notificationManager2.notify(101, notification2);
//
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        fb_db.child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Audios").child(audnode).setValue(aud);
//                        notificationBuilder2.setProgress(100, (int) progress2, false).setContentText("Progressed (" + (int) progress2 + "%)").setOngoing(false).setDefaults(android.app.Notification.DEFAULT_ALL);
//                        notification2 = notificationBuilder2.build();
//                        notificationManager2.notify(101, notification2);
//                        Uploader.astate=false;
//                        Toast.makeText(getApplicationContext(), "Audio Uploaded", Toast.LENGTH_LONG).show();
////                                    Intent i3 = new Intent(HomeFrag.this, HomeFrag.class);
////                                    i3.putExtra("bool1", false);
////                                    startActivity(i3);
////                                    finish();
//
//                    }
//                });
//
//            return "SUCCESS";
//        }
//
//
//        // This runs in UI when background thread finishes
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (result.equals("SUCCESS"))
//                System.out.println("SUCCESS");
//
//
//            //    progressDialog.dismiss();
//            // Do things like hide the progress bar or change a TextView
//        }
//
//        public String getFileName(Uri uri) {
//            String result = null;
//            if (uri.getScheme().equals("content")) {
//                Cursor cursor = NewTabActivity.this.getContentResolver().query(uri, null, null, null, null);
//                try {
//                    if (cursor != null && cursor.moveToFirst()) {
//                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                    }
//                } finally {
//                    cursor.close();
//                }
//            }
//            if (result == null) {
//                result = uri.getPath();
//                int cut = result.lastIndexOf('/');
//                if (cut != -1) {
//                    result = result.substring(cut + 1);
//                }
//            }
//            return result;
//        }
//    }
//
//    private class MyTaskv extends AsyncTask<String, Integer, String> {
//
//        // Runs in UI before background thread is called
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //     progressDialog = ProgressDialog.show(getActivity(), "Message", "Uploading Image...");
//
//        }
//
//        // This is run in a background thread
//        @Override
//        protected String doInBackground(String... params) {
//
//
//
//
//            vid = new VideoDesc();
//            vid.setUser(CurrentUser.user);
//
//            vid.setDesc(vdesc);
//            vid.setMaincat(Category.maincat);
//            vid.setSubcat(Category.subcat);
//
//
//     vid.setDate(new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()));
//
//                if (tar.equals("GROUP")) {
//                    vid.setTarget("all");
//                    vid.setTargetmems(new ArrayList<String>());
//                } else if (tar.equals("INDIVIDUAL")) {
//                    vid.setTarget("indi");
//                    ArrayList targetmems = new ArrayList(tempmems);
//                    vid.setTargetmems(targetmems);
//
//                }
//                final String vidnode = CurrentUser.user + vid.getDate();
//                System.out.println("  videonode  " + vidnode);
//                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference().child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Videos").child(vidnode);
//
//                System.out.println("Storage refference : " + storageReference3);
//
//                final NotificationManager notificationManager3 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                final android.app.Notification.Builder notificationBuilder3 = new android.app.Notification.Builder(getApplicationContext());
//                notificationBuilder3.setOngoing(true)
//                        .setContentTitle("Uploading Video")
//                        .setContentText("Progressed (" + (int) progress3 + " %)")
//                        .setProgress(100, (int) progress3, false)
//                        .setSmallIcon(R.drawable.soul_logo);
//                notification3 = notificationBuilder3.build();
//                notificationManager3.notify(102, notification3);
//
//                UploadTask up2 = storageReference3.putFile(VideoUri);
//                up2.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        progress3 = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                        System.out.println("Progress is " + progress3);
//                        notificationBuilder3.setProgress(100, (int) progress3, false).setContentText("Progressed (" + (int) progress3 + "%...)");
//                        notification3 = notificationBuilder3.build();
//                        notificationManager3.notify(102, notification3);
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        fb_db.child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Videos").child(vidnode).setValue(vid);
//
//                        progress3 = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                        System.out.println("Progress is " + progress3);
//                        notificationBuilder3.setProgress(100, (int) progress3, false).setContentText("Progressed (" + (int) progress3 + "%...)").setOngoing(false).setDefaults(android.app.Notification.DEFAULT_ALL);
//                        notification3 = notificationBuilder3.build();
//                        notificationManager3.notify(102, notification3);
//                        Uploader.vstate=false;
//                        Toast.makeText(getApplicationContext(), "Video Uploaded", Toast.LENGTH_LONG).show();
////                                    Intent i4 = new Intent(HomeFrag.this, HomeFrag.class);
////                                    i4.putExtra("bool1", false);
////                                    startActivity(i4);
////                                    finish();
//
//                    }
//                });
//
//
//            return "SUCCESS";
//        }
//
//
//        // This runs in UI when background thread finishes
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (result.equals("SUCCESS")) {
//                System.out.println("SUCCESS");
//
//            }
//
//            //    progressDialog.dismiss();
//            // Do things like hide the progress bar or change a TextView
//        }
//
//        public String getFileName(Uri uri) {
//            String result = null;
//            if (uri.getScheme().equals("content")) {
//                Cursor cursor = NewTabActivity.this.getContentResolver().query(uri, null, null, null, null);
//                try {
//                    if (cursor != null && cursor.moveToFirst()) {
//                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                    }
//                } finally {
//                    cursor.close();
//                }
//            }
//            if (result == null) {
//                result = uri.getPath();
//                int cut = result.lastIndexOf('/');
//                if (cut != -1) {
//                    result = result.substring(cut + 1);
//                }
//            }
//            return result;
//        }
//    }
//    private class MyTaskf extends AsyncTask<String, Integer, String> {
//
//        // Runs in UI before background thread is called
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // progressDialog = ProgressDialog.show(Files_PU.this, "Message", "Uploading File...");
//
//        }
//
//        // This is run in a background thread
//        @Override
//        protected String doInBackground(String... params) {
//            // get the string from params, which is an array
//            try {
//                fil = new FileDesc();
//                fil.setUser(CurrentUser.user);
//
//                fil.setDesc(fdesc);
//                fil.setMaincat(Category.maincat);
//                fil.setSubcat(Category.subcat);
//                System.out.println("Mangatha dawww");
//
//
//                    System.out.println("FDESC2" + fil.getDesc());
//
//                    fil.setDate(new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()));
//
//                    if (tar.equals("GROUP")) {
//                        fil.setTarget("all");
//                        fil.setTargetmems(new ArrayList<String>());
//                    } else if (tar.equals("INDIVIDUAL")) {
//                        fil.setTarget("indi");
//                        ArrayList targetmems = new ArrayList(tempmems);
//                        fil.setTargetmems(targetmems);
//
//                    }
//
//
//                    final String filenode = CurrentUser.user + fil.getDate();
//                    System.out.println("  filenode  " + filenode);
//                    StorageReference storageReference4 = FirebaseStorage.getInstance().getReference().child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Files").child(filenode);
//
//                    System.out.println("Storage refference : " + storageReference4);
//
//                    final NotificationManager notificationManager4 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                    final android.app.Notification.Builder notificationBuilder4 = new android.app.Notification.Builder(getApplicationContext());
//                    notificationBuilder4.setOngoing(true)
//                            .setContentTitle("Uploading File")
//                            .setContentText("Progressed (" + (int) progress4 + " %)")
//                            .setProgress(100, (int) progress4, false)
//                            .setSmallIcon(R.drawable.soul_logo);
//                    notification4 = notificationBuilder4.build();
//                    notificationManager4.notify(104, notification4);
//
//                    UploadTask up3 = storageReference4.putFile(fileUri);
//                    up3.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            progress4 = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                            System.out.println("Progress is " + progress4);
//                            notificationBuilder4.setProgress(100, (int) progress4, false).setContentText("Progressed (" + (int) progress4 + "%...)");
//                            notification4 = notificationBuilder4.build();
//                            notificationManager4.notify(104, notification4);
//                        }
//                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            fb_db.child("Classes").child(CurrentUser.sclass).child(CurrentUser.ssec).child("Files").child(filenode).setValue(fil);
//
//                            notificationBuilder4.setProgress(100, (int) progress4, false).setContentText("Progressed (" + (int) progress4 + "%)").setOngoing(false).setDefaults(android.app.Notification.DEFAULT_ALL);
//                            notification4 = notificationBuilder4.build();
//                            notificationManager4.notify(104, notification4);
//                            Uploader.fstate=false;
//                            Toast.makeText(NewTabActivity.this, "File Uploaded", Toast.LENGTH_LONG).show();
////                                    Intent i5 = new Intent(HomeFrag.this, HomeFrag.class);
////                                    i5.putExtra("bool1", false);
////                                    startActivity(i5);
////                                    finish();
//
//
//                        }
//                    });
//
//
//
//
//
//                return "SUCCESS";
//
//            } catch (Exception e) {
////                Toast.makeText(getActivity(), "Network Busy!!", Toast.LENGTH_LONG).show();
//                //System.out.println("Error SMS "+e);
//
//            }
//
//            return "FAILED";
//        }
//
//
//
//        // This runs in UI when background thread finishes
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if(result.equals("SUCCESS")){
//                System.out.println("SUCCESS");
//
//            }
//
//
//            //   progressDialog.dismiss();
//            // Do things like hide the progress bar or change a TextView
//        }
//        public String getFileName(Uri uri) {
//            String result = null;
//            if (uri.getScheme().equals("content")) {
//                Cursor cursor = NewTabActivity.this.getContentResolver().query(uri, null, null, null, null);
//                try {
//                    if (cursor != null && cursor.moveToFirst()) {
//                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                    }
//                } finally {
//                    cursor.close();
//                }
//            }
//            if (result == null) {
//                result = uri.getPath();
//                int cut = result.lastIndexOf('/');
//                if (cut != -1) {
//                    result = result.substring(cut + 1);
//                }
//            }
//            return result;
//        }
//    }
//
//}
