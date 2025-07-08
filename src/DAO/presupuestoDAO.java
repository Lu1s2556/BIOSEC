package DAO;

import Modelo.EXAMENES;
import utilidades.ConexionBD;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Burrx
 */
public class presupuestoDAO {
    
    
    
    public List<EXAMENES> BuscarPrueba(String txt){
        List<EXAMENES> resultado;
        resultado = new ArrayList<>();
        String sentencia = "SELECT * FROM pruebas WHERE nombre LIKE ?";
        
        try(Connection con = ConexionBD.getConexion();
            PreparedStatement PS = con.prepareStatement(sentencia);) {
            
            PS.setString(1, txt+"%");
            ResultSet rs = PS.executeQuery();
            
            while(rs.next()){
                resultado.add(new EXAMENES(rs.getInt("ID_PRUEBA"), rs.getString("NOMBRE")));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
}
