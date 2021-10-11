package com.example.ECO_Parcial1_Client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity<Sting> extends AppCompatActivity {
    public EditText editText, editNum, posX, posY;
    public Button buttonV;
    public Button buttonA;
    public Button buttonCrear;
    public Button buttonConfirmar;
    public Socket socket; //AAAAAAA
    public BufferedReader reader;
    public BufferedWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posX = findViewById(R.id.PosX);
        posY = findViewById(R.id.PosY);
        editText = findViewById(R.id.editText);
        editNum = findViewById(R.id.editNum);


        Button btnR = findViewById(R.id.buttonR);
        buttonA = findViewById(R.id.buttonA);
        buttonV = findViewById(R.id.buttonV);
        buttonConfirmar = findViewById(R.id.buttonConfirmar);
        buttonCrear = findViewById(R.id.buttonCrear);
        initCliente();

        btnR.setOnClickListener(
            (v)->{
           String x = posX.getText().toString();
           String y = posY.getText().toString();
           EnviarMensaje(x+":"+y);



    }
    );
    }
    public void initCliente() {
        new Thread(
                ()->{
                    try{
                        Socket socket = new Socket("192.168.100.91", 8000);

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        reader = new BufferedReader(isr);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osw);

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void EnviarMensaje(String msj){
        new Thread(
                ()->{

                    try{
                      writer.write(msj + "\n");
                        writer.flush();

                } catch (IOException e) {
                        e.printStackTrace();
                    }
        }
        ).start();

   }

}

