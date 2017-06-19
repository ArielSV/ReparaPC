package com.oscod.arielsv.reparapcapp.Equipos;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.oscod.arielsv.reparapcapp.R;
import com.oscod.arielsv.reparapcapp.Users.Consulta;
import com.oscod.arielsv.reparapcapp.Users.DetailPagerAdapter;
import com.oscod.arielsv.reparapcapp.Users.Registro;

public class DetalleEquipo extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView backButton;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);
        Bundle extras = getIntent().getExtras();
        backButton=(ImageView) findViewById(R.id.backbutton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addFragments();
        tabLayout.setupWithViewPager(viewPager);
        addIcon();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void addIcon(){
        Drawable myIcon = getResources().getDrawable( R.drawable.plus_filled_50 );
        Drawable myIcon2 = getResources().getDrawable( R.drawable.group_of_questions_filled_50 );
        ColorFilter filter = new LightingColorFilter( Color.WHITE, Color.WHITE);
        myIcon.setColorFilter(filter);
        tabLayout.getTabAt(0).setIcon(R.drawable.plus_filled_50);
        ColorFilter filter2 = new LightingColorFilter( Color.WHITE, Color.WHITE);
        myIcon2.setColorFilter(filter2);
        tabLayout.getTabAt(1).setIcon(R.drawable.group_of_questions_filled_50);
//        tabLayout.getTabAt(2).setIcon(R.drawable.registry_editor_filled_50);
//        tabLayout.getTabAt(3).setIcon(R.drawable.delete_filled_50);
    }
    public void addFragments(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        DetailPagerAdapter adapter = new DetailPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RegistroEquipo(), "");
        adapter.addFragment(new ConsultaEquipo(), "");
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
    }
}
