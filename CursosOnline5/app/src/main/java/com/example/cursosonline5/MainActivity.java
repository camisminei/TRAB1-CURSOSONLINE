package com.example.cursosonline5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listAluno;
    private Button btnNovoAluno;
    Aluno aluno;
    DBAlunoHelper alunoHelper;
    ArrayList<Aluno> arrayList;
    ArrayAdapter<Aluno> arrayAdapter;
    private int id1,id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAluno=findViewById(R.id.listAlunos);
        btnNovoAluno=findViewById(R.id.btnNovoCadastro);
        registerForContextMenu(listAluno);
        preencherLista();
        btnNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Cadastro.class);
                startActivity(it);
            }
        });
        listAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno a1=(Aluno)arrayAdapter.getItem(position);
                Intent it=new Intent(MainActivity.this, Cadastro.class);
                it.putExtra("ch_aluno",a1);
                startActivity(it);
            }
        });
        listAluno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view, int position, long id){
                aluno = arrayAdapter.getItem(position);
                return false;
            }
        });
    }
    public void preencherLista(){
        alunoHelper = new DBAlunoHelper(MainActivity.this);
        arrayList=alunoHelper.selecionaAllAlunos();
        alunoHelper.close();
        if(arrayList!=null){
            arrayAdapter=new ArrayAdapter<Aluno>(MainActivity.this,
                    android.R.layout.simple_expandable_list_item_1, arrayList);
            listAluno.setAdapter(arrayAdapter);
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        preencherLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1,"Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2,"Cancela");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                alunoHelper = new DBAlunoHelper(MainActivity.this);
                retornoBD = alunoHelper.deleteAluno(aluno);
                alunoHelper.close();
                if(retornoBD==-1){
                    alert("Erro de exclusão!");
                }
                else{
                    alert("Registro excluído com sucesso!");
                }
                preencherLista();
                return false;        }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}