package sehatmaru.memberid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import sehatmaru.memberid.realms.ItemRealm;

public class WelcomePage extends AppCompatActivity {

    EditText emailET;

    String inputEmail;

    private static Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        emailET = findViewById(R.id.emailET);
        Button signInBtn = findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(view -> verifyEmail());

        deleteItemRealm();
        insertItemRealm();
    }

    private void verifyEmail(){
        inputEmail = emailET.getText().toString();

        if (inputEmail.equals("sehatmaru@gmail.com")){
            Intent intent = new Intent(getApplicationContext(), FeedPage.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Email Address is not exists", Toast.LENGTH_SHORT).show();
        }
    }

    public static void insertItemRealm(){
        realm.beginTransaction();

        ItemRealm itemRealm = realm.where(ItemRealm.class).equalTo("id", "1").findFirst();

        if (itemRealm==null){
            ItemRealm itemRealm1 = realm.createObject(ItemRealm.class, "1");
            itemRealm1.setName("Mobile Card");
            itemRealm1.setPoin("10.000");
            itemRealm1.setType("Voucher");
            itemRealm1.setGift("Gift Card 100.000");

            ItemRealm itemRealm2 = realm.createObject(ItemRealm.class, "2");
            itemRealm2.setName("Phone Card");
            itemRealm2.setPoin("30.000");
            itemRealm2.setType("Voucher");
            itemRealm2.setGift("Gift Card 300.000");

            ItemRealm itemRealm3 = realm.createObject(ItemRealm.class, "3");
            itemRealm3.setName("Cake Card");
            itemRealm3.setPoin("50.000");
            itemRealm3.setType("Product");
            itemRealm3.setGift("Old Fasion Cake");
        }

        realm.commitTransaction();
    }

    public static void deleteItemRealm(){
        realm.beginTransaction();
        realm.where(ItemRealm.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
}
