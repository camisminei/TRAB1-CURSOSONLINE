package com.example.cursosonline5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    private EditText edtNome, edtEmail, edtNomeCurso, edtQtdHoras, edtCpf, edtTelefone;
    Aluno aluno, altAluno;
    DBAlunoHelper alunoHelper;
    long retornoDB;
    private Button btnVariavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        edtNome=findViewById(R.id.edtNome);
        edtEmail=findViewById(R.id.edtEmail);
        edtNomeCurso=findViewById(R.id.edtNomeCurso);
        edtQtdHoras=findViewById(R.id.edtqtHoras);
        edtCpf=findViewById(R.id.edtCpf);
        edtTelefone=findViewById(R.id.edtTelefone);
        Intent it = getIntent();
        altAluno=(Aluno)it.getSerializableExtra("ch_aluno");
        aluno = new Aluno();
        alunoHelper=new DBAlunoHelper(Cadastro.this);
        btnVariavel=findViewById(R.id.btnVariavel);

        if(altAluno!=null){
            btnVariavel.setText("ALTERAR");
            edtNome.setText(altAluno.getNome());
            edtEmail.setText(altAluno.getEmail());
            edtCpf.setText(altAluno.getCpf());
            edtTelefone.setText(altAluno.getTelefone());
            edtNomeCurso.setText(altAluno.getNomeCurso());
            edtQtdHoras.setText(altAluno.getQtdHoras());
            aluno.setId(altAluno.getId());
        }else{
            btnVariavel.setText("SALVAR");
        }
        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome=edtNome.getText().toString();
                String email=edtEmail.getText().toString();
                String cpf=edtCpf.getText().toString();
                String telefone=edtTelefone.getText().toString();
                String curso=edtNomeCurso.getText().toString();
                String qtdHoras=edtQtdHoras.getText().toString();
                long retornoDB;
                aluno.setNome(nome);
                aluno.setEmail(email);
                aluno.setCpf(cpf);
                aluno.setTelefone(telefone);
                aluno.setNomeCurso(curso);
                aluno.setQtdHoras(qtdHoras);
                if(btnVariavel.getText().toString().equals("SALVAR")){
                    retornoDB=alunoHelper.inserir(aluno);
                    if (retornoDB==-1){
                        Toast.makeText(Cadastro.this, "Erro ao cadastrar",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Cadastro.this, "Cadastro Realizado com Sucesso",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    alunoHelper.atualizar(aluno);
                    alunoHelper.close();
                }
                finish();
            }
        });
    }
}