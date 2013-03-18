package cn.fyg.pa.application.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.WorkflowService;
import cn.fyg.pa.domain.model.process.ProcessFile;



@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {
	
	@Autowired
	RepositoryService repositoryService;

	@Override
	public List<ProcessFile> getProcessFile(String directoryPath) throws FileNotFoundException {
		File directory = new File(directoryPath);
		if(!directory.exists())
			throw new FileNotFoundException(String.format("directoryPath:[%s] not exists", directoryPath));
		String[] files = directory.list();
		List<ProcessFile> fileList = new ArrayList<ProcessFile>();
		for (int i = 0; i < files.length; i++) {
			ProcessFile processFile = new ProcessFile();
			processFile.setName(files[i].toString());
			fileList.add(processFile);
		}
		return fileList;
		
	}

	@Override
	@Transactional
	public void deployFile(String directoryPath, String filename)
			throws FileNotFoundException {
		String fullFilePath = directoryPath+ File.separator + filename;
		ZipInputStream inputStream = new ZipInputStream(new FileInputStream(fullFilePath));
		repositoryService.createDeployment()
		    .name(filename)
		    .addZipInputStream(inputStream)
		    .deploy();

	}

}
