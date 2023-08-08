package br.com.samuelives.printidtcp.infra.printers.printer

data class PrinterData(
    val vendor: String,
    val model: String,
    val serial: String?,
    val firmwareVersion: String?
)