package br.ufrn.sigestagios.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * Created by YuriAlessandro on 23/03/2018.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();
        versionElement.setTitle("Versão 0.15");

        Element authorYuri = new Element();
        Element authorPedro = new Element();
        Element authorGustavo = new Element();
        Element authorJoao = new Element();

        authorYuri.setTitle("Yuri Alessandro Martins")
            .setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = getApplicationContext();
                        try {
                            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/100002085072063")));
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yuri.alessandro.m")));
                        }
                    }
        });

        authorGustavo.setTitle("Gustavo Araújo Carvalho");
        authorJoao.setTitle("João Emmanuel");
        authorPedro.setTitle("Pedro Arthur Medeiros");


        View aboutPage = new AboutPage(this)
                .setDescription("O Sistema de Gerenciamento de Oportunidades (SIGOP) é um sistema " +
                                "desenvolvido para que os alunos da Universidade Federal do Rio Grande " +
                                "do Norte possam estar atualizados ao que diz respeito às ofertas de " +
                                "bolsas e estágios.\n Foi também desenvolvido para que empresas/pessoas" +
                                "interessadas em encontrar estagiários possam possuir uma maneira simples" +
                                "de encontrar as melhores opções.")
                .isRTL(false)
                .addItem(versionElement)
                .addGroup("Autores e Colaboradores")
                .addItem(authorGustavo)
                .addItem(authorJoao)
                .addItem(authorPedro)
                .addItem(authorYuri)
                .addWebsite("https://github.com/YuriAlessandro/SIGOP")
                .create();
        setContentView(aboutPage);
    }
}
