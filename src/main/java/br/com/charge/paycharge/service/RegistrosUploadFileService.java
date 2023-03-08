package br.com.charge.paycharge.service;

import br.com.charge.paycharge.dto.RegistroUploadFileDto;
import br.com.charge.paycharge.repository.RegistrosUploadFileRepository;
import br.com.charge.paycharge.model.RegistrosUploadFile;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrosUploadFileService {

    @Autowired
    private RegistrosUploadFileRepository registrosUploadFileRepository;

    public RegistroUploadFileDto inserir(RegistrosUploadFile ruf) {
        registrosUploadFileRepository.save(ruf);

        return new RegistroUploadFileDto(ruf);
    }
}
