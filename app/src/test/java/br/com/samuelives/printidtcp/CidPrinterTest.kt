package br.com.samuelives.printidtcp

import br.com.samuelives.printidtcp.infra.printers.cidprinter.CIDPrinter
import org.junit.Test

class CidPrinterTest {

    @Test
    fun tcpConnectionTest() {

        val printer = CIDPrinter("0.0.0.0", 9100)

        val isConnected = printer.connect()
        assert(isConnected) { "Falha ao se conectar com a impressora via TCP/IP: ${printer.lastError()}" }

        val data = printer.printerData()
        println("Printer data ${data.vendor} model ${data.model} serial number ${data.serial} firmware ${data.firmwareVersion}")
    }

}