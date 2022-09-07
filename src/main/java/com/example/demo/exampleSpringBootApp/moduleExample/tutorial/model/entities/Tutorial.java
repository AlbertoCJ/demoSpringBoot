package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.exampleSpringBootApp.domain.enums.TutorialType;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tutorials")
public class Tutorial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "published")
	private boolean published;
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tutorial_type")
	private TutorialType tutorialType;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "tutorial_autor",
		joinColumns = @JoinColumn(name = "tutorial_id"),
		inverseJoinColumns = @JoinColumn(name = "autor_id"))
	private Set<Autor> autors;


	public Tutorial() {
		super();
		this.autors = new HashSet<>();
	}
	public Tutorial(String title, String description, boolean published, TutorialType tutorialType) {
		this.title = title;
		this.description = description;
		this.published = published;
		this.tutorialType = tutorialType;
		this.autors = new HashSet<>();
	}

}
