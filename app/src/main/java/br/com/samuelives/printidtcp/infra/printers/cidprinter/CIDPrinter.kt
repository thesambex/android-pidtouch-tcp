package br.com.samuelives.printidtcp.infra.printers.cidprinter

import br.com.samuelives.printidtcp.infra.printers.printer.PrinterData
import br.com.samuelives.printidtcp.infra.printers.printer.PrinterInterface
import com.controlid.cidprinter.CidPrinter

class CIDPrinter(private val host: String, private val port: Int) : PrinterInterface {

    private val printer = CidPrinter(host, port)

    private var _error: String? = null
    val error: String? get() = _error

    override fun connect(): Boolean {

        try {
            printer.Iniciar()
        } catch (e: Exception) {
            _error = e.message
            return false
        }

        return true
    }

    override fun printerData(): PrinterData {
        return PrinterData(
            printer.GetManufacturersName(),
            printer.GetModelName(),
            printer.GetSerialNumber(),
            printer.GetFirmwareVersion()
        )
    }

    override fun lastError(): String? = error

    companion object {
        private const val TAG = "cid_printer"
    }
}