package com.example.cursosonline5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBAlunoHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cursosonline5.db";
    private static final String TABLE_NAME = "cursosonline";
    private static final String COLUM_ID = "id";
    private static final String COLUM_NOME = "nome";
    private static final String COLUM_EMAIL = "email";
    private static final String COLUM_CPF = "cpf";
    private static final String COLUM_TELEFONE = "telefone";
    private static final String COLUM_CURSO = "curso";
    private static final String COLUM_QTDHORAS = "qtdHoras";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table cursosonline " +
            "(id integer primary key autoincrement, nome text not null, " +
            "email text not null, cpf text not null, curso text not null," +
            "qtdHoras text not null, telefone text not null);";

    public DBAlunoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //REALIZA A CONSULTA
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME; //VERIFICA SE EXISTE A TABELA
        db.execSQL(query);
        onCreate(db);
    }

    public long inserir(Aluno a){
        long retornoDB;
        db=this.getWritableDatabase(); //pedir permissão para escrever
        ContentValues values = new ContentValues();
        values.put(COLUM_NOME, a.getNome());
        values.put(COLUM_EMAIL, a.getEmail());
        values.put(COLUM_CPF, a.getCpf());
        values.put(COLUM_TELEFONE, a.getTelefone());
        values.put(COLUM_CURSO, a.getNomeCurso());
        values.put(COLUM_QTDHORAS, a.getQtdHoras());
        retornoDB=db.insert(TABLE_NAME, null, values);
        String res = Long.toString(retornoDB);
        Log.i("DBAlunoHelper", res);
        db.close();
        return retornoDB;
    }

    public ArrayList<Aluno> selecionaAllAlunos(){
        String[] coluns = {COLUM_ID, COLUM_NOME, COLUM_EMAIL, COLUM_CPF, COLUM_CURSO,
                COLUM_QTDHORAS, COLUM_TELEFONE};
        Cursor cursor=getReadableDatabase().query(TABLE_NAME, coluns, null,
                null, null, null, "upper(nome)", null);
        ArrayList<Aluno> listAluno = new ArrayList<Aluno>();
        while(cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setEmail(cursor.getString(2));
            a.setCpf(cursor.getString(3));
            a.setNomeCurso(cursor.getString(4));
            a.setQtdHoras(cursor.getString(5));
            a.setTelefone(cursor.getString(6));
            listAluno.add(a);
        }
        return listAluno;
    }
    public long atualizar(Aluno a){
        long retorno;
        db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_NOME, a.getNome());
        values.put(COLUM_EMAIL, a.getEmail());
        values.put(COLUM_CPF, a.getCpf());
        values.put(COLUM_CURSO, a.getNomeCurso());
        values.put(COLUM_QTDHORAS, a.getQtdHoras());
        values.put(COLUM_TELEFONE, a.getTelefone());
        String[] args = {String.valueOf(a.getId())};
        retorno=db.update(TABLE_NAME,values,"id=?",args);
        db.close();
        return retorno;
    }
    public long deleteAluno(Aluno a){
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(a.getId())};
        retornoBD=db.delete(TABLE_NAME, COLUM_ID+"=?",args);
        return retornoBD;
    }
}
