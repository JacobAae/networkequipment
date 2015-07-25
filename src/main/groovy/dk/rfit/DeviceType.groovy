package dk.rfit

enum DeviceType {

	EX2200_C_12T(12),
	EX2200_24T(24),
	EX3200_24T(24),

	EX3200_48T(24),

	EX3300_24(24),

	EX4200_24T(24),

	EX4200_48T(24),

	EX_VC(24),


	SRX210BE(24),
	SRX240H(24),
	SRX240H2(24),
	SRX_CLUSTER(24),


	ACX1100_AC(24),
	ACX1100_DC(24),
	MX80(2)

	final int ports

	private DeviceType(int ports) {
		this.ports = ports
	}


}
