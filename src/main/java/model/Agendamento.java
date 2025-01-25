package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int paciente;
    int atendente;
    int medico;
    String dataHora;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPaciente() {
        return paciente;
    }
    
    public void setPaciente(int paciente) {
        this.paciente = paciente;
    }
    
    public int getAtendente() {
        return atendente;
    }
    
    public void setAtendente(int atendente) {
        this.atendente = atendente;
    }
    
    public int getMedico() {
        return medico;
    }
    
    public void setMedico(int medico) {
        this.medico = medico;
    }
    
    public String getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
    
}
