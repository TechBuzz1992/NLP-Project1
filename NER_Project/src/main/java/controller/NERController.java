package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import core.Pipeline;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import model.Type;

@RestController
@RequestMapping(value ="/api/v1")
public class NERController {
	@Autowired(required = true)
	private StanfordCoreNLP stanfordCoreNLP;
	
	@PostMapping
	@RequestMapping(value ="/ner")
	public Set<String> getNER(@RequestBody final String text,@RequestParam final Type type){
		stanfordCoreNLP = Pipeline.getInstance();
		CoreDocument coreDocument = new CoreDocument(text);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreLabel> coreLabelList = coreDocument.tokens();
		
		return new HashSet<>(collectList(coreLabelList,type));
		
	}
	
	public List<String> collectList(List<CoreLabel> coreLabelList,final Type type){
		return coreLabelList
		.stream()
		.filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
		.map(CoreLabel :: originalText)
		.collect(Collectors.toList());
	}

}
