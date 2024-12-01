/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Anggotas {
    private int idanggota;
    private String nama;
    private String alamat;
    private String telepon;
    
    public Anggotas ()
    {
    
    }
    
    public Anggotas (String nama, String Alamat, String telepon)
    {
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public int getIdanggota() {
        return idanggota;
    }

    public void setIdanggota(int idanggota) {
        this.idanggota = idanggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return this.telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    
    
    public Anggotas getById(int id){
        Anggotas agt = new Anggotas();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota " + " WHERE idanggota = '" + id + "'");
        try
        {
            while(rs.next())
            {
                agt = new Anggotas();
                agt.setIdanggota (rs.getInt("idanggota"));
                agt.setNama (rs.getString("nama"));
                agt.setAlamat (rs.getString("alamat"));
                agt.setTelepon(rs.getString("telepon"));
            }
        }
            catch (Exception e)
        {
            e.printStackTrace();
        }
            return agt;
    }
    
    public ArrayList<Anggotas> getAll()
    {
            ArrayList<Anggotas> ListAnggota = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM Anggota");
        try
        {
            while(rs.next())
            {
                Anggotas agt = new Anggotas(); 
                agt.setIdanggota (rs.getInt("idanggota"));
                agt.setNama (rs.getString("nama"));
                agt.setAlamat(rs.getString("Alamat"));
                agt.setTelepon(rs.getString("telepon"));
                ListAnggota.add(agt);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListAnggota;
    }
    
    public ArrayList<Anggotas> search (String keyword)
    {
        ArrayList<Anggotas> ListAnggota = new ArrayList(); 
        String sql = "SELECT * FROM anggota WHERE " +
        "nama LIKE '%" + keyword + "%' "
        + " OR alamat LIKE '%" + keyword + "%' ";
        
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try
        {
            while(rs.next())
            {
                Anggotas agt = new Anggotas(); 
                agt.setIdanggota (rs.getInt("idanggota"));
                agt.setNama (rs.getString("nama"));
                agt.setAlamat(rs.getString("Alamat"));
                agt.setTelepon(rs.getString("telepon"));
                ListAnggota.add(agt);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListAnggota;
    }
    
    public void save() {
        if (getById(idanggota).getIdanggota() == 0) {
            // Perbaikan pada query INSERT: hapus koma berlebih
            String SQL = "INSERT INTO anggota (nama, alamat, telepon) VALUES ("
                       + "'" + this.nama + "', "
                       + "'" + this.alamat + "', "
                       + "'" + this.telepon + "')";
            this.idanggota = DBHelper.insertQueryGetId(SQL);
        } else {
            // Perbaikan pada query UPDATE: hapus koma sebelum WHERE
            String SQL = "UPDATE anggota SET "
                       + "nama = '" + this.nama + "', "
                       + "alamat = '" + this.alamat + "', "
                       + "telepon = '" + this.telepon + "' "
                       + "WHERE idanggota = '" + this.idanggota + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    
    public void delete()
    {
        String SQL = "DELETE FROM anggota WHERE idanggota = '" + this.idanggota + "'";
        DBHelper.executeQuery(SQL);
    }
}
