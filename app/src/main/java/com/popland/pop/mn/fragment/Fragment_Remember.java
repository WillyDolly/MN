package com.popland.pop.mn.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.popland.pop.mn.MainActivity;
import com.popland.pop.mn.R;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hai on 29/05/2017.
 */
//free 16 , allo 118 -> 0.5 ; 106
public class Fragment_Remember extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
View v;
int digits = 1;
    LinearLayout llTextView, llNumberPicker;
    float secondLeft;
    int specificSecond = 30;
    CountDownTimer cdtAnimation, cdtTime;
    int r=0;

TextView tvNumberLimit, tvNumber, tvNumberFurther,
         tvTimeLimit, tvTime, tvTimeFurther;
    ImageButton ibOK;
    SeekBar sbNumber, sbTime;
TextSwitcher textSwitcher;
ProgressBar cirleProgressBar;
int TIME, NUMBER;
int tiendo;
String[][] MajorNumber = new String[11][10];
    public static ArrayList<String> NumberGroups;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.remember_fragment,container,false);
        Toast.makeText(getActivity(),"Remember111",Toast.LENGTH_SHORT).show();
        Log.i("SonCreatView",specificSecond+"");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("SonViewCreated", specificSecond + "");
        Toast.makeText(getActivity(), "Remember222", Toast.LENGTH_SHORT).show();
        mapping();
        for(int r=0;r<11;r++){
            for(int c=0;c<10;c++){
                if(r==0)
                    MajorNumber[r][c] = String.valueOf(c);
                else
                    MajorNumber[r][c] = String.valueOf(r-1)+c;
            }
        }
        textSwitcherSetting();
        sbNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress + 1;
                tvNumber.setText(progress  + " số");
                NUMBER = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvNumberLimit.setVisibility(View.VISIBLE);
                tvNumberFurther.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvNumberLimit.setVisibility(View.GONE);
                tvNumberFurther.setVisibility(View.GONE);
            }
        });

        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = (50 - progress) * 100;
                tvTime.setText(progress + " ms");
                TIME = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvTimeLimit.setVisibility(View.VISIBLE);
                tvTimeFurther.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvTimeLimit.setVisibility(View.GONE);
                tvTimeFurther.setVisibility(View.GONE);
            }
        });

        ibOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llNumberPicker.setVisibility(View.GONE);
                MainActivity.ibHethong.setEnabled(false);
                MainActivity.ibNho.setEnabled(false);

//                String randomNu = String.valueOf(RNG(NUMBER));
//                //b. divide into groups
//                int separator = 0;
//                int result = NUMBER / 2;
//                int mod = NUMBER % 2;
//                int SoGroup = result + mod;
//                groups = new String[SoGroup];
//                if (NUMBER % 2 == 0) {
//                    for (int i = 0; i < SoGroup; i++)
//                        groups[i] = randomNu.substring(separator, separator += 2);
//                } else {
//                    for (int i = 0; i < SoGroup; i++) {
//                        if (i == (SoGroup - 1))
//                            groups[i] = randomNu.substring(separator, separator += 1);
//                        else
//                            groups[i] = randomNu.substring(separator, separator += 2);
//                    }
//                }
                NumberGroups = null;
                NumberGroups = new ArrayList<>();
                tiendo = 0;
                startCountDownTimer();
            }
        });
    }

    public void mapping(){
        llNumberPicker = (LinearLayout) v.findViewById(R.id.llNumberPicker);
        tvNumberLimit = (TextView) v.findViewById(R.id.tvNumberLimit);
        tvNumber = (TextView) v.findViewById(R.id.tvNumber);
        tvNumberFurther = (TextView) v.findViewById(R.id.tvNumberFurther);
        tvTimeLimit = (TextView) v.findViewById(R.id.tvTimeLimit);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
        tvTimeFurther = (TextView) v.findViewById(R.id.tvTimeFurther);
        sbNumber = (SeekBar) v.findViewById(R.id.sbNumber);
        sbTime = (SeekBar) v.findViewById(R.id.sbTime);
        ibOK = (ImageButton) v.findViewById(R.id.ibOK);
        textSwitcher = (TextSwitcher)v.findViewById(R.id.textSwitcher);
        cirleProgressBar = (ProgressBar)v.findViewById(R.id.circleProgressBar);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ibOK){

        }
    }

    public BigInteger RNG(int d){
        BigInteger min, max, result;
        if(d==1)
            min = BigInteger.ZERO;
        else
            min = BigInteger.TEN.pow(d-1);
        max = BigInteger.TEN.pow(d).subtract(BigInteger.ONE);
        do{
            result = min.add(new BigInteger(max.bitLength(),new SecureRandom()));
        }while(result.compareTo(max)>0);
        return result;
    }

    public void textSwitcherSetting(){
       Animation in =  AnimationUtils.makeInAnimation(getActivity(),false);
        Animation out = AnimationUtils.makeOutAnimation(getActivity(),false);
       textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView text = new TextView(getActivity());
                text.setGravity(Gravity.CENTER_HORIZONTAL);
                text.setTextSize(24);
                text.setTextColor(Color.parseColor("#12a6f5"));
                return text;
            }
        });
    }

    public void startCountDownTimer(){
        cirleProgressBar.setMax(TIME*(NUMBER+1));
        cirleProgressBar.setProgress(TIME*(NUMBER+1));
        tiendo = TIME;
//        final CountDownTimer cdtProgress = new CountDownTimer(TIME,1) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                cirleProgressBar.setProgress(tiendo++);
//            }
//
//            @Override
//            public void onFinish() {
//                cirleProgressBar.setProgress(TIME);
//                tiendo = 0;
//            }
//        };

        CountDownTimer cdtNumber = new CountDownTimer(TIME*(NUMBER+1),TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                int row, column;
                Random random = new Random();
                row = random.nextInt(11);
                column = random.nextInt(10);
                textSwitcher.setText(MajorNumber[row][column]);
                NumberGroups.add(MajorNumber[row][column]);
                cirleProgressBar.setProgress((int)millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getActivity(),NumberGroups.size()+"",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == sbNumber){

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}




//        //2. Button OK clicked

//                // c. put in TextViews
//                for(int i=0;i<groups.length;i++){
//                    TextView tv = new TextView(getActivity());
//                    tv.setBackgroundColor(Color.CYAN);
//                    tv.setTextSize(24);
//                    tv.setText(groups[i]);
//                    llTextView.addView(tv);//LinearLayout expand automatically
//                }
//
//
//
//                //c. separate TextView
//                cdtAnimation = new CountDownTimer(4000,1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        r+=5;
//                        for(int i=0;i<llTextView.getChildCount();i++){
//                            llTextView.getChildAt(i).setPadding(r,r,r,r);// CountDownTimer + set_ = Animation
//                        }
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        //3. run Time
//                        r = 0;
//                        tvTime.setEnabled(true);
//                        cdtTime = new CountDownTimer(30000, 1) {
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//                                secondLeft = (float)millisUntilFinished/1000;
//                                String strDu = "0."+String.format("%03d",millisUntilFinished%1000);
//                                float soDu = Float.parseFloat(strDu);
//                                int soNguyen = Math.round(secondLeft - soDu);
//                                if(soNguyen!=specificSecond){
//                                    specificSecond = soNguyen;
//                                    tvTime.setText(String.valueOf(specificSecond));
//                                }
//                            }
//
//                            @Override
//                            public void onFinish() {
//                               Move_Restore();
//                            }
//                        }.start();
//                    }
//                }.start();
//            }
//        });
//
//        tvTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cdtTime.cancel();
//                Move_Restore();
//            }
//        });
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    void Move_Restore(){
//        if(MainActivity.apf.getCount()<3) {
//            MainActivity.apf.AddFragment(new Fragment_Retrieve());
//            MainActivity.apf.notifyDataSetChanged();
//            Toast.makeText(getActivity(),"count "+MainActivity.apf.getCount(),Toast.LENGTH_SHORT).show();
//        }
//        MainActivity.viewPager.setCurrentItem(2,true);
//
//        llNumberPicker.setVisibility(View.VISIBLE);
//        if(llTextView.getChildCount()>0) {
//            llTextView.removeAllViews();
//            Log.i("llTextView ","removed");
//        }
//        specificSecond = 30;
//        tvTime.setText(String.valueOf(specificSecond));
//        tvTime.setEnabled(false);
//
//        MainActivity.ibNho.setBackgroundColor(Color.LTGRAY);
//        MainActivity.ibHoituong.setBackgroundColor(Color.GREEN);
//    }
//
//



