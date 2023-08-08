package br.com.samuelives.printidtcp.infra.printers.printer

interface PrinterInterface {
    fun connect(): Boolean
    fun printerData(): PrinterData
    fun lastError(): String?
}