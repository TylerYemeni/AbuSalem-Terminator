package com.abusalem.guard.vpn

import android.content.Context
import android.util.Log
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URL
import java.util.*

object AdvancedVPNManager {

    private val vpnServers = listOf(
        "185.199.229.156:8080",
        "45.88.106.123:3128",
        "91.205.172.10:1080",
        "37.27.4.234:9050"
    )

    private var currentProxy: Proxy? = null
    private var currentIP: String = ""

    fun connectNewVPN(context: Context): Boolean {
        return try {
            val proxyAddress = vpnServers.random()
            val (host, port) = proxyAddress.split(":")
            val proxy = Proxy(Proxy.Type.SOCKS, InetSocketAddress(host, port.toInt()))
            currentProxy = proxy

            // اختبار الاتصال من خلال البروكسي
            val url = URL("http://api.ipify.org")
            val connection = url.openConnection(proxy)
            connection.connectTimeout = 5000
            connection.getInputStream().use {
                val ip = it.bufferedReader().readText().trim()
                currentIP = ip
                Log.i("VPN", "تم الاتصال بـ IP: $ip")
                true
            }
        } catch (e: Exception) {
            Log.e("VPN", "فشل الاتصال بـ VPN: ${e.message}")
            false
        }
    }

    fun getCurrentIP(): String {
        return currentIP
    }

    fun getCurrentProxy(): Proxy? {
        return currentProxy
    }
}
