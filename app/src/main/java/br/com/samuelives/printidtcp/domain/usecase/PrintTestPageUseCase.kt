package br.com.samuelives.printidtcp.domain.usecase

import android.os.Build
import android.util.Log
import br.com.samuelives.printidtcp.common.Resource
import br.com.samuelives.printidtcp.domain.repository.SettingsRepository
import br.com.samuelives.printidtcp.infra.printers.cidprinter.CIDPrinter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PrintTestPageUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(): Resource<Boolean> {

        val printerSettings = settingsRepository.printerSettings()

        val printer = CIDPrinter(printerSettings.host!!, printerSettings.port)
        if (!printer.connect())
            return Resource.Error(printer.lastError().orEmpty())

        val printerData = printer.printerData()

        val sb = StringBuilder()

        sb.append("Printer: ").append(printerData.model).append("\n")
        sb.append("Vendor: ").append(printerData.vendor).append("\n")
        sb.append("Serial Number: ").append((printerData.serial ?: "N/A")).append("\n")
        sb.append("Firmware version: ").append((printerData.firmwareVersion ?: "N/A")).append("\n\n")

        sb.append("Phone model: ").append(Build.MODEL).append("\n\n")

        sb.append("{qr}https://www.controlid.com.br/automacao-comercial/printid-touch/{qr}")

        printer.print(sb.toString())

        return Resource.Success(true)
    }

}