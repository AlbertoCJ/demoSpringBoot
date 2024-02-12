package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.filters;

import com.example.demo.exampleSpringBootApp.commons.filter.SpecificationsBuilder;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.data.jpa.domain.Specification;

public class TutorialFilter {

	private SpecificationsBuilder<Tutorial> spec = new SpecificationsBuilder<>();
	
	public static TutorialFilter init() {
    	return new TutorialFilter();
    }
	
	
	
	public TutorialFilter addTitle(String title) {
		if (title != null) {
            spec.with(TutorialSpecification.TITLE, title, false);
        }
		return this;
	}
	
	public TutorialFilter addDescription(String desc) {
		if (desc != null) {
            spec.with(TutorialSpecification.DESCRIPTION, desc, false);
        }
		return this;
	}
	
	public TutorialFilter addPublished(Boolean published) {
		if (published != null) {
            spec.with(TutorialSpecification.PUBLISHED, published, false);
        }
		return this;
	}
	
	public TutorialFilter addAutorName(String autorName) {
		if (autorName != null) {
            spec.with(TutorialSpecification.AUTOR_NAME, autorName, false);
        }
		return this;
	}
	
	
    public Specification<Tutorial> getTutorialSpecification() {
        return Specification.where(spec.build(TutorialSpecification::new));
    }

}
