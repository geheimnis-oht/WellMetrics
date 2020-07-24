package io.laidani.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * TODO : Verification needed Chuck -- Mandrins ?!!
 */
public class Chuck {
	/*
	 * Chuck = Mandrin (Clapet 1/8, 1/16 , ...)
	 */

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Column (name ="CHECK_TYPE")
	private ChuckType chuckType;
	
	@Column(name = "PLUGGED_ON")
	private LocalDate pluggedOn;
	
	@ManyToOne
	@JoinColumn (name = "WUI")
	private Well well;
	
}
