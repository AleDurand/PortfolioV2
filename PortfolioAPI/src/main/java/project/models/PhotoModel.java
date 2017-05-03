package project.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image")
public class PhotoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "path", nullable = false)
	private String path;
	
	@Column(name = "ts_create", insertable = false)
	private Date creationTime;

	@OneToOne	
	@JsonIgnore
	@JoinColumn(name = "album_id")
	private AlbumModel album;
	
	public PhotoModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@PrePersist
	protected void onCreate() {
		this.creationTime = new Date();
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public AlbumModel getAlbum() {
		return album;
	}

	public void setAlbum(AlbumModel album) {
		this.album = album;
	}

}
