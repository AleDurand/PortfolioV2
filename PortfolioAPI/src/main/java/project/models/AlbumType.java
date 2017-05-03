package project.models;

public enum AlbumType {
	UNKNOWN(0), COVERS(1), BIRTHDAY(2), WEDDING(3), OTHER(4);

	private int value;

	private AlbumType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
