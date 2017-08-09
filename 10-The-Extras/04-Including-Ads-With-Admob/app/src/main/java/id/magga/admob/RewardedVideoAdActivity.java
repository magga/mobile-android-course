package id.magga.admob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class RewardedVideoAdActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd mAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video_ad);

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }

    public void ViewVideo(View view){
        if (mAd.isLoaded()) {
            mAd.show();
        } else {
            Toast.makeText(this, "Video Not Ready Yet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "Rewarded Video Ad Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "Rewarded Video Ad Opened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "Rewarded Video Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "Rewarded Video Ad Closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Toast.makeText(this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "Rewarded Video Ad Left Application", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "Rewarded Video Ad Failed To Load Error Code : " + Integer.toString(i), Toast.LENGTH_SHORT).show();
    }
}
