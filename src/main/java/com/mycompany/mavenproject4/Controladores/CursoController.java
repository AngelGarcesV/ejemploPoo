package com.mycompany.mavenproject4.Controladores;
import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.repositorios.CursoRepo;
import com.mycompany.mavenproject4.repositorios.ProgramaRepo;
import java.util.List;

public class CursoController {
    private static ProgramaRepo repositorioPrograma = new ProgramaRepo();
    private static CursoRepo repositorioCurso = new CursoRepo();

    public Curso createCurso(Programa infoPrograma, Boolean activo){
        Curso infoCurso = new Curso(null,infoPrograma,activo);

        return infoCurso.isValid() ? repositorioCurso.crearCurso(infoCurso) : null;
    }

    public Boolean deleteCurso(Long id){
            return repositorioCurso.eliminarCurso(id);
    }

    public Curso getCursoById(Long id){
        return repositorioCurso.obtenerCursoByID(id);
    }

    public List<Curso> getAllCursos(){
        return repositorioCurso.obtenerTodosCursos();
    }

    public boolean updateCursoById(Long id, Programa infoPrograma, Boolean activo){
        Curso infoCurso = new Curso(id,infoPrograma,activo);
        return infoCurso.isValid() ? CursoRepo.actualizarCursoPorId(infoCurso) != null : false;
    }

}
