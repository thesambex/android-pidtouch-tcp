package br.com.samuelives.printidtcp.infra.printers.cidprinter

import br.com.samuelives.printidtcp.infra.printers.printer.PrinterData
import br.com.samuelives.printidtcp.infra.printers.printer.PrinterInterface
import com.controlid.cidprinter.CidPrinter

class CIDPrinter(private val host: String, private val port: Int) : PrinterInterface {

    private val printer = CidPrinter(host, port)

    private var _error: String? = null

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

    override fun lastError(): String? = _error

    override fun print(text: String) {
        try {

            // O objetivo aqui é apenas imprimir o qr code separadamente, não processar o texto
            val parts = text.split("{qr}")
            for ((index, part) in parts.withIndex()) {
                if (index % 2 == 0) {
                    printer.SendStringToPrinter(part)
                } else {
                    printer.ImprimirCodigoQR(part)
                }
            }


            printer.AtivarGuilhotina(CidPrinter.TipoCorte.TOTAL)
            printer.Finalizar()

        } catch (e: Exception) {
            _error = e.message
        }
    }

    companion object {
        private const val TAG = "cid_printer"
    }
}