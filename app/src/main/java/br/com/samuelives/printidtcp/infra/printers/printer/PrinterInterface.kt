package br.com.samuelives.printidtcp.infra.printers.printer

/**
 * Implement another printers
 */
interface PrinterInterface {
    fun connect(): Boolean
    fun printerData(): PrinterData
    fun lastError(): String?
    fun print(text: String)
}