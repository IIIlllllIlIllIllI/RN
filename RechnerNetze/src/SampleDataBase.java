import java.util.ArrayList;

/**
* Sample set of emails for the POP Server implementation.
 */
public class SampleDataBase {
    public static ArrayList<String> messages;
    static {
        messages = new ArrayList<String>(12);
        messages.add(
                "Return-Path: <fourfold@foxathome.com>\n" +
                "X-Original-To: roadrunner@dept.acme.org\n" +
                "Delivered-To: roadrunner@dept.acme.org\n" +
                "Received: from mailman.dept.acme.org (mailman-dept.acme.org [10.0.0.139])\n" +
                "\tby mail.dept.acme.org (Postfix) with ESMTP id 8EA0A11C8\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:56 +0200 (CEST)\n" +
                "Received: from localhost (localhost [127.0.0.1])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 8C8A2BE1\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:55 +0200 (CEST)\n" +
                "Received: from mailman.dept.acme.org ([127.0.0.1])\n" +
                "\tby localhost (mailman-dept.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id 04817-02 for <roadrunner@dept.acme.org>;\n" +
                "\tMon, 8 Sep 2008 05:10:55 +0200 (CEST)\n" +
                "Received: from mx.acme.org (mx.acme.org [143.205.180.45])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 584D181C\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:55 +0200 (CEST)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id DF29B154001\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:48 +0200 (CEST)\n" +
                "X-Spam-Checker-Version: SpamAssassin 3.2.3 (2007-08-08) on mx.acme.org\n" +
                "X-Spam-Level: ***\n" +
                "X-Spam-Status: No, score=3.2 required=4.5 tests=BAYES_40,DCC_CHECK,\n" +
                "\tHTML_MESSAGE autolearn=no version=3.2.3\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id C28EE154002\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:48 +0200 (CEST)\n" +
                "X-Greylist: Passed host: 10.11.2.202\n" +
                "Received: from mailrelay.tugraz.at (mailrelay.someserver.org [10.11.2.202])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id A98C3154001\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:48 +0200 (CEST)\n" +
                "Received: from firewall.ecma.org (firewall.emca.org [10.11.219.242])\n" +
                "\tby mailrelay2.tugraz.at (8.14.2/8.14.2) with ESMTP id m883AkBx001435\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 8 Sep 2008 05:10:46 +0200 (CEST)\n" +
                "Received: from exchange2.emca.org (exchange2.emca.org [192.168.111.5])\n" +
                "\tby firewall.ecma.org (Postfix) with ESMTP id B934E7AE2FB\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon,  8 Sep 2008 05:10:45 +0200 (CEST)\n" +
                "Received: from firewall.emca.org ([192.168.111.1]) by exchange2.emca.org with Microsoft SMTPSVC(6.0.3790.3959);\n" +
                "\t Mon, 8 Sep 2008 05:10:45 +0200\n" +
                "Received: from mailgate.tugraz.at (mailgate.someserver.org [10.11.2.200])\n" +
                "\tby firewall.emca.org (Postfix) with ESMTP id 6C4727AE2FB\n" +
                "\tfor <swat@emca.org>; Mon,  8 Sep 2008 05:10:45 +0200 (CEST)\n" +
                "Received: from aaei159.neoplus.adsl.tpnet.pl (ayw137.neoplus.adsl.tpnet.pl [83.27.134.137])\n" +
                "\tby mailgate2.tugraz.at (8.14.2/8.14.2) with SMTP id m883AV7T026402\n" +
                "\tfor <swat@emca.org>; Mon, 8 Sep 2008 05:10:39 +0200 (CEST)\n" +
                "Date: Mon, 08 Sep 2008 03:10:33 +0000\n" +
                "From: \"Mussel Deas\" <fourfold@foxathome.com>\n" +
                "X-Mailer: The Bat! (3.65.01) Professional\n" +
                "Reply-To: Mussel Deas <fourfold@foxathome.com>\n" +
                "X-Priority: 3 (Normal)\n" +
                "Message-ID: <5758119675.20080908030654@danielwernli.ch>\n" +
                "To: <swat@emca.org>\n" +
                "Subject: Single-horned  'Unicorn' deer foundd in Italy\n" +
                "MIME-Version: 1.0\n" +
                "Content-Type: multipart/alternative;\n" +
                " boundary=\"----------67F9168D4B0A76\"\n" +
                "X-Scanned-By: MIMEDefang 2.64 on 10.11.10.19\n" +
                "X-Scanned-By: MIMEDefang 2.64 on 10.11.10.3\n" +
                "X-Scanned-By: milter-sender/1.16.916  (mailgate2 [10.11.10.3]); Mon, 08 Sep 2008 05:10:42 +0200\n" +
                "X-TUGAntiSpamFlag: ham\n" +
                "X-OriginalArrivalTime: 08 Sep 2008 03:10:45.0779 (UTC) FILETIME=[7C5D4630:01C91160]\n" +
                "X-Virus-Scanned: ClamAV using ClamSMTP\n" +
                "X-Virus-Scanned: amavisd-new at mailman.dept.acme.org\n" +
                "\n" +
                "------------67F9168D4B0A76\n" +
                "Content-Type: text/plain; charset=iso-8859-1\n" +
                "Content-Transfer-Encoding: quoted-printable\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "In the house of representatives as prolonged and the year.\n" +
                "thus lincoln's advent in the ohio campaign everliberal and\n" +
                "puissant baladeva having worshipped of character has been\n" +
                "preserved than could have the way some rishis, of bodies\n" +
                "of the measure.\n" +
                "\n" +
                "------------67F9168D4B0A76\n" +
                "Content-Type: text/html; charset=iso-8859-1\n" +
                "Content-Transfer-Encoding: quoted-printable\n" +
                "\n" +
                "\n" +
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
                "\n" +
                "<html><head><title></title>=09\n" +
                "<META http-equiv=3DContent-Type content=3D\"text/html; charset=3D\"iso-8859-1=\n" +
                "\">\n" +
                "</head>\n" +
                "\n" +
                "<body>  <a name=3D\"#rpwr\">=09</a><br><a name=3D\"#tttp\">   </a><table width=\n" +
                "=3D\"59\" border=3D\"0\">\n" +
                "\n" +
                "<TR vAlign=3Dbottom>\n" +
                "\n" +
                "<td rowSpan=3D\"2\" nowrap>F</TD><TD><font color=3D\"#DDEBC3\">a</font></TD>\n" +
                "  <td rowSpan=3D\"2\" nowrap>A</TD>   <TD></TD>\n" +
                "\n" +
                "<td rowSpan=3D\"2\" nowrap>&nbsp;V</TD>   <TD><font color=3D\"#EBC3E7\">c</font=\n" +
                "></TD>\n" +
                "\n" +
                "<td rowSpan=3D\"2\" nowrap>A&nbsp;</TD>   <TD><font color=3D\"#EBCDC3\">0</font=\n" +
                "></TD>\n" +
                "<td rowSpan=3D\"2\" nowrap>your</TD>=09<TD><font color=3D\"#CBC3EB\">9</font></=\n" +
                "TD>\n" +
                "\n" +
                "<td rowSpan=3D\"2\" nowrap>n</TD><TD><font color=3D\"#EBD8C3\">f</font></TD>\n" +
                " <td rowSpan=3D\"2\" nowrap>reality</TD><TD><font color=3D\"#C3EBDD\">g</font><=\n" +
                "/TD>\n" +
                "  </tr>\n" +
                "=0A<tr vAlign=3Dbottom>\n" +
                "<td nowrap>EM</TD>\n" +
                "  <td nowrap>LE</TD>\n" +
                "\n" +
                "<td nowrap>IAGR</TD>\n" +
                "<td nowrap>-&nbsp;</TD>\n" +
                "=09<td nowrap>&nbsp;</TD>\n" +
                "   <td nowrap>ew&nbsp;</TD>\n" +
                "\n" +
                "<td nowrap>!</TD>\n" +
                "</tr>\n" +
                "  =0A</table>\n" +
                "<strong> </strong><table width=3D\"24\" border=3D\"0\">\n" +
                "\n" +
                "<TR vAlign=3Dbottom>\n" +
                "<td rowSpan=3D\"2\" nowrap>WW</TD><TD><font color=3D\"#EBC3D9\">l</font></TD>\n" +
                "   <td rowSpan=3D\"2\" nowrap>.N</TD><TD><font color=3D\"#EBC3C4\">y</font></TD=\n" +
                ">\n" +
                "\n" +
                "<td rowSpan=3D\"2\" nowrap>R</TD>   <TD><font color=3D\"#D4EBC3\">D</font></TD>\n" +
                "\n" +
                "<td rowSpan=3D\"2\" nowrap>NE</TD> <TD></TD>\n" +
                "=09</tr>\n" +
                "   =0A<tr vAlign=3Dbottom>\n" +
                "   <td nowrap>W</TD>\n" +
                " <td nowrap>OLE</TD>\n" +
                "=09<td nowrap>.</TD>\n" +
                "<td nowrap>T</TD>\n" +
                " </tr>\n" +
                " =0A</table><br>\n" +
                "\n" +
                "<span name=3D\"#qwqq\"></span><br><span> </span><p><br></p><a name=3D\"#qqqr\">=\n" +
                "   </a>\n" +
                "<p><span name=3D\"#ptrt\">  </span>In the house of representatives as prolong=\n" +
                "ed and the year.<br>   thus lincoln's advent in the ohio campaign everliber=\n" +
                "al and<br>   puissant baladeva having worshipped of character has been<br> =\n" +
                "  preserved than could have the way some rishis, of bodies<br>   of the mea=\n" +
                "sure.</p>\n" +
                "\n" +
                "</body></html>\n" +
                "------------67F9168D4B0A76--\n" +
                "");
        messages.add(
                "Return-Path: <PROFILES@GRUPOPROFILES.COM>\n" +
                "X-Original-To: roadrunner@dept.acme.org\n" +
                "Delivered-To: roadrunner@dept.acme.org\n" +
                "Received: from mailman.dept.acme.org (mailman-dept.acme.org [10.0.0.139])\n" +
                "\tby mail.dept.acme.org (Postfix) with ESMTP id 0B29F453A\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:35 +0200 (CEST)\n" +
                "Received: from localhost (localhost [127.0.0.1])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 87A8D3A70\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:35 +0200 (CEST)\n" +
                "Received: from mailman.dept.acme.org ([127.0.0.1])\n" +
                "\tby localhost (mailman-dept.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id 31039-07 for <roadrunner@dept.acme.org>;\n" +
                "\tWed, 10 Sep 2008 11:18:35 +0200 (CEST)\n" +
                "Received: from mx.acme.org (mx.acme.org [143.205.180.45])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 5846B889\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:35 +0200 (CEST)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 545F2154006\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:28 +0200 (CEST)\n" +
                "X-Spam-Checker-Version: SpamAssassin 3.2.3 (2007-08-08) on mx.acme.org\n" +
                "X-Spam-Level: *\n" +
                "X-Spam-Status: No, score=1.6 required=4.5 tests=BAYES_00,HTML_MESSAGE,\n" +
                "\tRCVD_NUMERIC_HELO,RDNS_NONE,SUBJ_ALL_CAPS autolearn=no version=3.2.3\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 39DAA15400A\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:28 +0200 (CEST)\n" +
                "X-Greylist: Passed host: 212.170.213.71\n" +
                "Received: from mail33b.webhostingvirtual.com (unknown [212.170.213.71])\n" +
                "\tby mx.acme.org (Postfix) with SMTP id E8523154006\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:27 +0200 (CEST)\n" +
                "Received: from 212.170.213.46 (212.170.213.46)\n" +
                "\tby mail33b.webhostingvirtual.com (RS ver 1.0.95vs) with SMTP id 1-0732168306\n" +
                "\tfor <roadrunner@dept.acme.org>; Wed, 10 Sep 2008 11:18:26 +0200 (CEST)\n" +
                "Received: (qmail 44682 invoked from network); 10 Sep 2008 09:18:26 -0000\n" +
                "Received: from unknown (HELO COREL) (PROFILES@83.58.68.199)\n" +
                "  by  with ESMTPA; 10 Sep 2008 09:18:26 -0000\n" +
                "Message-ID: <1896EDA4BDD5418A829C0CE938072AD1@COREL>\n" +
                "Reply-To: \"MANUFACTURA TEXTIL\" <PROFILES@PROFILES.ES>\n" +
                "From: \"MANUFACTURA TEXTIL\" <PROFILES@GRUPOPROFILES.COM>\n" +
                "To: <\"Undisclosed-Recipient:;\"@mx.acme.org>\n" +
                "Subject: OFERTA DE CAMISETAS PERSONALIZADAS\n" +
                "Date: Wed, 10 Sep 2008 11:18:16 +0200\n" +
                "Organization: WWW.GRUPOPROFILES.COM\n" +
                "MIME-Version: 1.0\n" +
                "Content-Type: multipart/alternative;\n" +
                "\tboundary=\"----=_NextPart_000_00EC_01C91336.EC0879F0\"\n" +
                "X-Priority: 1\n" +
                "X-MSMail-Priority: High\n" +
                "X-Mailer: Microsoft Windows Mail 6.0.6001.18000\n" +
                "Disposition-Notification-To: \"MANUFACTURA TEXTIL\" <PROFILES@GRUPOPROFILES.COM>\n" +
                "X-MimeOLE: Produced By Microsoft MimeOLE V6.0.6001.18049\n" +
                "To:undisclosed-recipients:;\n" +
                "X-SF-Loop: 1\n" +
                "X-Virus-Scanned: ClamAV using ClamSMTP\n" +
                "X-Virus-Scanned: amavisd-new at mailman.dept.acme.org\n" +
                "\n" +
                "This is a multi-part message in MIME format.\n" +
                "\n" +
                "------=_NextPart_000_00EC_01C91336.EC0879F0\n" +
                "Content-Type: text/plain;\n" +
                "\tcharset=\"iso-8859-1\"\n" +
                "Content-Transfer-Encoding: quoted-printable\n" +
                "\n" +
                "Buenas dias\n" +
                "\n" +
                "Les informo del catalogo: www.rolymadrid.com\n" +
                "         CAMISETAS M/L, POLOS M/L, SUDADERAS, CHAQUETAS, FORROS POLARES, =\n" +
                "PARKAS Y CORTAVIENTOS\n" +
                "CAMISETAS ROLY 135GRS + SERIGRAFIA 1 COLOR DESDE 1 EURO.\n" +
                "         =20\n" +
                "un cordial saludo\n" +
                "\n" +
                "GRUPO PROFILES\n" +
                "902 050 474  ( 9:00 A 15:00H ) LUNES A VIERNES\n" +
                "WWW.GRUPOPROFILES.COM\n" +
                "\n" +
                "________________________=20\n" +
                "\n" +
                "Servicio de Informaci=F3n al cliente\n" +
                "Tel=E9fono: 902 050 474\n" +
                "Correo: profiles@grupoprofiles.com\n" +
                "Si no quieres recivir mas informacion envien un email con el asunto =\n" +
                "BORRAR=20\n" +
                "AVISO: Las informaciones ofrecidas por este medio tienen exclusivamente =\n" +
                "car=E1cter ilustrativo y no originar=E1n derechos, ni expectativas de =\n" +
                "derechos (Art=EDculo 4 del Decreto 204/1995, de 29 de Agosto, BOJA n=BA =\n" +
                "136, de 26 de Octubre).=20\n" +
                "\n" +
                "------=_NextPart_000_00EC_01C91336.EC0879F0\n" +
                "Content-Type: text/html;\n" +
                "\tcharset=\"iso-8859-1\"\n" +
                "Content-Transfer-Encoding: quoted-printable\n" +
                "\n" +
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
                "<HTML><HEAD>\n" +
                "\n" +
                "<META http-equiv=3DContent-Type content=3D\"text/html; =\n" +
                "charset=3Diso-8859-1\">\n" +
                "<META content=3D\"MSHTML 6.00.6001.18099\" name=3DGENERATOR>\n" +
                "<STYLE></STYLE>\n" +
                "</HEAD>\n" +
                "<BODY bgColor=3D#ffffff>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2>Buenas =\n" +
                "dias</FONT></DIV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2></FONT>&nbsp;</DIV>\n" +
                "<DIV><FONT face=3DArial size=3D2><FONT color=3D#000080>Les informo del =\n" +
                "catalogo:=20\n" +
                "\n" +
                "</FONT><A href=3D\"http://www.rolymadrid.com\"><STRONG><FONT face=3D\"Times =\n" +
                "New Roman\"=20\n" +
                "color=3D#000080 =\n" +
                "size=3D5>www.rolymadrid.com</FONT></STRONG></A></FONT></DIV>\n" +
                "<DIV><FONT face=3DArial size=3D2><STRONG><FONT face=3D\"Times New Roman\" =\n" +
                "color=3D#000080=20\n" +
                "size=3D3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; CAMISETAS M/L, =\n" +
                "POLOS=20\n" +
                "M/L, SUDADERAS, CHAQUETAS, FORROS POLARES, PARKAS Y=20\n" +
                "CORTAVIENTOS</FONT></STRONG></FONT></DIV>\n" +
                "\n" +
                "<DIV><STRONG><FONT color=3D#800000>CAMISETAS ROLY 135GRS + SERIGRAFIA 1 =\n" +
                "COLOR=20\n" +
                "DESDE 1 EURO.</FONT></STRONG></DIV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080=20\n" +
                "size=3D2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; =\n" +
                "\n" +
                "</FONT><FONT=20\n" +
                "face=3DArial size=3D2></FONT></DIV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2>un cordial =\n" +
                "saludo</FONT></DIV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2></FONT>&nbsp;</DIV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2>GRUPO =\n" +
                "PROFILES</FONT></DIV>\n" +
                "\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2>902 050 474&nbsp; ( =\n" +
                "9:00 A 15:00H )=20\n" +
                "LUNES A VIERNES</FONT></DIV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2><A=20\n" +
                "href=3D\"http://www.GRUPOPROFILES.COM\">WWW.GRUPOPROFILES.COM</A></FONT></D=\n" +
                "IV>\n" +
                "<DIV><FONT face=3DArial color=3D#000080 size=3D2></FONT>&nbsp;</DIV>\n" +
                "<DIV><FONT color=3D#000080>________________________ <BR><BR>Servicio de=20\n" +
                "Informaci=F3n al cliente<BR>Tel=E9fono: 902 050 474<BR>Correo: </FONT><A =\n" +
                "\n" +
                "href=3D\"mailto:profiles@grupoprofiles.com\"><FONT=20\n" +
                "color=3D#000080>profiles@grupoprofiles.com</FONT></A><BR><FONT =\n" +
                "face=3DArial=20\n" +
                "color=3D#000080 size=3D2>Si no quieres recivir mas informacion envien un =\n" +
                "email con=20\n" +
                "el asunto&nbsp;BORRAR </FONT></DIV>\n" +
                "\n" +
                "<DIV><FONT color=3D#000080>AVISO: Las informaciones ofrecidas por este =\n" +
                "medio=20\n" +
                "tienen exclusivamente car=E1cter ilustrativo y no originar=E1n derechos, =\n" +
                "ni=20\n" +
                "expectativas de derechos (Art=EDculo 4 del Decreto 204/1995, de 29 de =\n" +
                "Agosto, BOJA=20\n" +
                "n=BA 136, de 26 de Octubre).&nbsp;<BR></FONT></DIV></BODY></HTML>\n" +
                "\n" +
                "------=_NextPart_000_00EC_01C91336.EC0879F0--\n" +
                "");
        messages.add(
                "Return-Path: <info@kathoferschambers.net78.net>\n" +
                "X-Original-To: roadrunner@dept.acme.org\n" +
                "Delivered-To: roadrunner@dept.acme.org\n" +
                "Received: from mailman.dept.acme.org (mailman-dept.acme.org [10.0.0.139])\n" +
                "\tby mail.dept.acme.org (Postfix) with ESMTP id 14D7E3AC5\n" +
                "\tfor <roadrunner@dept.acme.org>; Fri, 12 Sep 2008 03:35:49 +0200 (CEST)\n" +
                "Received: from localhost (localhost [127.0.0.1])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 19C3839C7\n" +
                "\tfor <roadrunner@dept.acme.org>; Fri, 12 Sep 2008 03:35:49 +0200 (CEST)\n" +
                "Received: from mailman.dept.acme.org ([127.0.0.1])\n" +
                "\tby localhost (mailman-dept.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id 18101-06 for <roadrunner@dept.acme.org>;\n" +
                "\tFri, 12 Sep 2008 03:35:49 +0200 (CEST)\n" +
                "Received: from mx.acme.org (mx.acme.org [143.205.180.45])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id E771EFFE\n" +
                "\tfor <roadrunner@dept.acme.org>; Fri, 12 Sep 2008 03:35:48 +0200 (CEST)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id A528C154006\n" +
                "\tfor <roadrunner@dept.acme.org>; Fri, 12 Sep 2008 03:35:41 +0200 (CEST)\n" +
                "X-Spam-Flag: YES\n" +
                "X-Spam-Checker-Version: SpamAssassin 3.2.3 (2007-08-08) on mx.acme.org\n" +
                "X-Spam-Level: ********\n" +
                "X-Spam-Status: Yes, score=8.2 required=4.5 tests=AWL,BAYES_50,DCC_CHECK,\n" +
                "\tDIGEST_MULTIPLE,RAZOR2_CF_RANGE_51_100,RAZOR2_CF_RANGE_E4_51_100,RAZOR2_CHECK,\n" +
                "\tRDNS_NONE,TVD_PH_SUBJ_URGENT autolearn=no version=3.2.3\n" +
                "X-Spam-Report: \n" +
                "\t*  2.8 TVD_PH_SUBJ_URGENT TVD_PH_SUBJ_URGENT\n" +
                "\t*  0.0 BAYES_50 BODY: Bayesian spam probability is 40 to 60%\n" +
                "\t*      [score: 0.5387]\n" +
                "\t*  0.5 RAZOR2_CHECK Listed in Razor2 (http://razor.sf.net/)\n" +
                "\t*  1.5 RAZOR2_CF_RANGE_E4_51_100 Razor2 gives engine 4 confidence level\n" +
                "\t*      above 50%\n" +
                "\t*      [cf: 100]\n" +
                "\t*  0.5 RAZOR2_CF_RANGE_51_100 Razor2 gives confidence level above 50%\n" +
                "\t*      [cf: 100]\n" +
                "\t*  3.4 DCC_CHECK Listed in DCC (http://rhyolite.com/anti-spam/dcc/)\n" +
                "\t*  0.0 DIGEST_MULTIPLE Message hits more than one network digest check\n" +
                "\t*  0.1 RDNS_NONE Delivered to trusted network by a host with no rDNS\n" +
                "\t* -0.6 AWL AWL: From: address is in the auto white-list\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 903B2154008\n" +
                "\tfor <roadrunner@dept.acme.org>; Fri, 12 Sep 2008 03:35:41 +0200 (CEST)\n" +
                "X-Greylist: Passed host: 202.153.107.198\n" +
                "Received: from mail.tungwahcsd.org (unknown [202.153.107.198])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 12FBA154006\n" +
                "\tfor <roadrunner@dept.acme.org>; Fri, 12 Sep 2008 03:35:40 +0200 (CEST)\n" +
                "Received: from localhost (mail.tungwahcsd.org [127.0.0.1])\n" +
                "\tby mail.tungwahcsd.org (Postfix) with ESMTP id E185D13004E5;\n" +
                "\tFri, 12 Sep 2008 08:37:19 +0800 (HKT)\n" +
                "X-Virus-Scanned: amavisd-new at tungwahcsd.org\n" +
                "Received: from mail.tungwahcsd.org ([127.0.0.1])\n" +
                "\tby localhost (mail.tungwahcsd.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id gjJb5bzzxWMC; Fri, 12 Sep 2008 08:37:19 +0800 (HKT)\n" +
                "Received: from tungwahcsd.org (mail.tungwahcsd.org [127.0.0.1])\n" +
                "\tby mail.tungwahcsd.org (Postfix) with ESMTP id E7B721300798;\n" +
                "\tFri, 12 Sep 2008 08:37:11 +0800 (HKT)\n" +
                "From: \"Mrs. Wendy Jones\" <info@kathoferschambers.net78.net>\n" +
                "Reply-To: info.kathofer@yahoo.com\n" +
                "Subject: ***SPAM*** URGENT\n" +
                "Date: Fri, 12 Sep 2008 08:37:11 +0800\n" +
                "Message-Id: <20080912003353.M75884@kathoferschambers.net78.net>\n" +
                "X-Mailer: OpenWebMail 2.52 20060502\n" +
                "X-OriginatingIP: 77.220.30.29 (mwfyhe)\n" +
                "MIME-Version: 1.0\n" +
                "Content-Type: text/plain;\n" +
                "\tcharset=iso-8859-1\n" +
                "To: undisclosed-recipients:;\n" +
                "X-Virus-Scanned: ClamAV using ClamSMTP\n" +
                "X-Spam-Prev-Subject: URGENT\n" +
                "X-Virus-Scanned: amavisd-new at mailman.dept.acme.org\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "I am Mrs Wendy Jones. I have willed all my assets including my companies and\n" +
                "my investment to you.My doctor has just confirmed to me that i have few\n" +
                "minutes to live. so please contact my law firm. They will guide you on what to\n" +
                "do.(info.kathofer@yahoo.com)+2348061609928. Regards\n" +
                "Mrs Wendy Jones.\n" +
                "");
        messages.add(
                "Return-Path: <4@gmail.com>\n" +
                "X-Original-To: roadrunner@dept.acme.org\n" +
                "Delivered-To: roadrunner@dept.acme.org\n" +
                "Received: from mailman.dept.acme.org (mailman-dept.acme.org [10.0.0.139])\n" +
                "\tby mail.dept.acme.org (Postfix) with ESMTP id 46CFA3910\n" +
                "\tfor <roadrunner@dept.acme.org>; Thu, 11 Sep 2008 08:57:26 +0200 (CEST)\n" +
                "Received: from localhost (localhost [127.0.0.1])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 2996338EE\n" +
                "\tfor <roadrunner@dept.acme.org>; Thu, 11 Sep 2008 08:57:26 +0200 (CEST)\n" +
                "Received: from mailman.dept.acme.org ([127.0.0.1])\n" +
                "\tby localhost (mailman-dept.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id 09115-09 for <roadrunner@dept.acme.org>;\n" +
                "\tThu, 11 Sep 2008 08:57:26 +0200 (CEST)\n" +
                "Received: from mx.acme.org (mx.acme.org [143.205.180.45])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 0C3A7BE1\n" +
                "\tfor <roadrunner@dept.acme.org>; Thu, 11 Sep 2008 08:57:26 +0200 (CEST)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id D3D1B154009\n" +
                "\tfor <roadrunner@dept.acme.org>; Thu, 11 Sep 2008 08:57:18 +0200 (CEST)\n" +
                "X-Spam-Checker-Version: SpamAssassin 3.2.3 (2007-08-08) on mx.acme.org\n" +
                "X-Spam-Level: *\n" +
                "X-Spam-Status: No, score=1.2 required=4.5 tests=BAYES_20,INVALID_MSGID\n" +
                "\tautolearn=no version=3.2.3\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id C61A215400A\n" +
                "\tfor <roadrunner@dept.acme.org>; Thu, 11 Sep 2008 08:57:18 +0200 (CEST)\n" +
                "X-Greylist: Passed host: 87.250.77.13\n" +
                "Received: from leon.fauser.edu (leon.fauser.edu [87.250.77.13])\n" +
                "\tby mx.acme.org (Postfix) with SMTP id 6EC2B154009\n" +
                "\tfor <roadrunner@dept.acme.org>; Thu, 11 Sep 2008 08:57:17 +0200 (CEST)\n" +
                "Received: (qmail 12489 invoked by uid 509); 11 Sep 2008 06:57:17 -0000\n" +
                "Received: from 143.103.179.60.broad.nb.zj.dynamic.163data.com.cn by leon.fauser.edu (envelope-from <4@gmail.com>, uid 89) with qmail-scanner-2.05 \n" +
                " (clamdscan: 0.93.3/7733. spamassassin: 3.2.5.  \n" +
                " Clear:RC:1(60.179.103.143):SA:1(9.3/5.0):. \n" +
                " Processed in 2.225856 secs); 11 Sep 2008 06:57:17 -0000\n" +
                "Received: from 143.103.179.60.broad.nb.zj.dynamic.163data.com.cn (HELO DM) (60.179.103.143)\n" +
                "  by leon.fauser.edu with SMTP; 11 Sep 2008 06:57:12 -0000\n" +
                "Received: from -.gmail.com (HELO Delldim5150) ([220.130.53.4]) by -.gmail.com with ESMTP; Fri, 12 Sep 2008 06:46:30 -0400\n" +
                "Date: Fri, 12 Sep 2008 04:44:30 -0600\n" +
                "From: \"Adela Barrow\" <4@gmail.com>\n" +
                "To: moustapha.diaby@business.uconn.edu\n" +
                "Cc: moustm@otenet.gr, mousumi.sarkar@naccrra.org, mouthhouse-on@lists.higherthings.org, morphew@eagle.cc.ukans.edu, morr@deangroup.ca, roadrunner@conpoint.com, roadrunner@dept.acme.org, mluyu@chinaren.com, mluzzi@rdg.boehringer-ingelheim.com\n" +
                "Subject: We have data for Nursing Homes, Acupuncturists, Physical Therapists etc..\n" +
                "Message-ID: <145770e6nra0$g8661nc0$4850d8l0@Delldim5150\n" +
                "MIME-Version: 1.0\n" +
                "Content-type: text/plain; charset=US-ASCII\n" +
                "Priority: normal\n" +
                "X-Virus-Scanned: ClamAV using ClamSMTP\n" +
                "X-Virus-Scanned: amavisd-new at mailman.dept.acme.org\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Fully Licensed Physicians in America \n" +
                "\n" +
                "36 primary and secondary specialties\n" +
                "\n" +
                "you can sort by many different fields like state or zip\n" +
                "\n" +
                "Regular price is $497 but this week you only pay: $390\n" +
                "\n" +
                "\n" +
                "{}{}{} If you order this week you will receive the 4 complimentary datasets below: {}{}{}\n" +
                "\n" +
                "==> Optometrists\n" +
                "\n" +
                "==> Visiting Nurses & RN's\n" +
                "\n" +
                "==> Massage Therapists\n" +
                "\n" +
                "==> Acupuncturists\n" +
                "\n" +
                "contact:: BethanyRoberson@fceating.com\n" +
                "  \n" +
                "from today until this Friday ~~~~~~~~~~~~~~~~~~~~   To invoke no further correspondence status please send an email to hy456@fceating.com\n" +
                "");
        messages.add(
                "Return-Path: <xqnowoyl@median.ru>\n" +
                "X-Original-To: dept-assi@dept.acme.org\n" +
                "Delivered-To: dept-assi@dept.acme.org\n" +
                "Received: from mailman.dept.acme.org (mailman-dept.acme.org [10.0.0.139])\n" +
                "\tby mail.dept.acme.org (Postfix) with ESMTP id 0A0E87EF\n" +
                "\tfor <dept-assi@dept.acme.org>; Fri, 19 Sep 2008 19:49:13 +0200 (CEST)\n" +
                "Received: from localhost (localhost [127.0.0.1])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 0CDE242A3\n" +
                "\tfor <dept-assi@dept.acme.org>; Fri, 19 Sep 2008 19:49:13 +0200 (CEST)\n" +
                "Received: from mailman.dept.acme.org ([127.0.0.1])\n" +
                "\tby localhost (mailman-dept.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id 09291-08 for <dept-assi@dept.acme.org>;\n" +
                "\tFri, 19 Sep 2008 19:49:12 +0200 (CEST)\n" +
                "Received: from mx.acme.org (mx.acme.org [143.205.180.45])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id D0352C79\n" +
                "\tfor <dept-assi@dept.acme.org>; Fri, 19 Sep 2008 19:49:12 +0200 (CEST)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 7F7FC15400C\n" +
                "\tfor <dept-assi@dept.acme.org>; Fri, 19 Sep 2008 19:49:02 +0200 (CEST)\n" +
                "X-Spam-Flag: YES\n" +
                "X-Spam-Checker-Version: SpamAssassin 3.2.3 (2007-08-08) on mx.acme.org\n" +
                "X-Spam-Level: *****\n" +
                "X-Spam-Status: Yes, score=5.2 required=4.5 tests=BAYES_50,HTML_MESSAGE,\n" +
                "\tMIME_HTML_ONLY,SARE_SUB_CASINO,TW_FM,TW_ZR,URIBL_BLACK,URIBL_RHS_DOB\n" +
                "\tautolearn=no version=3.2.3\n" +
                "X-Spam-Report: \n" +
                "\t*  0.6 SARE_SUB_CASINO Spammer subject - gambling\n" +
                "\t*  0.1 TW_FM BODY: Odd Letter Triples with FM\n" +
                "\t*  0.1 TW_ZR BODY: Odd Letter Triples with ZR\n" +
                "\t*  0.0 HTML_MESSAGE BODY: HTML included in message\n" +
                "\t*  0.0 BAYES_50 BODY: Bayesian spam probability is 40 to 60%\n" +
                "\t*      [score: 0.4177]\n" +
                "\t*  1.5 MIME_HTML_ONLY BODY: Message only has text/html MIME parts\n" +
                "\t*  2.0 URIBL_BLACK Contains an URL listed in the URIBL blacklist\n" +
                "\t*      [URIs: firstcasinolite.com]\n" +
                "\t*  1.1 URIBL_RHS_DOB Contains an URI of a new domain (Day Old Bread)\n" +
                "\t*      [URIs: firstcasinolite.com]\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 57E9915400D\n" +
                "\tfor <dept-assi@dept.acme.org>; Fri, 19 Sep 2008 19:49:02 +0200 (CEST)\n" +
                "X-Greylist: Passed host: 143.205.174.164\n" +
                "Received: from smtp.isys.acme.org (darwin-isys.acme.org [143.205.174.164])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 3C365154005\n" +
                "\tfor <dept-assi@dept.acme.org>; Fri, 19 Sep 2008 19:49:02 +0200 (CEST)\n" +
                "Received: by smtp.isys.acme.org (Postfix)\n" +
                "\tid 6A3451590009; Fri, 19 Sep 2008 19:49:03 +0200 (CEST)\n" +
                "Delivered-To: dept-assi@isys.acme.org\n" +
                "Received: from localhost (localhost.localdomain [127.0.0.1])\n" +
                "\tby smtp.isys.acme.org (Postfix) with ESMTP id 6EA781590007\n" +
                "\tfor <assi@ifi.acme.org>; Fri, 19 Sep 2008 19:49:02 +0200 (CEST)\n" +
                "Received: from smtp.isys.acme.org ([127.0.0.1])\n" +
                " by localhost (smtp.isys.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                " with ESMTP id 21626-09 for <assi@ifi.acme.org>;\n" +
                " Fri, 19 Sep 2008 19:48:55 +0200 (CEST)\n" +
                "Received: from ncc0.median.ru (ncc1.median.ru [80.92.10.254])\n" +
                "\tby smtp.isys.acme.org (Postfix) with ESMTP id 0D40E1590004\n" +
                "\tfor <assi@ifi.acme.org>; Fri, 19 Sep 2008 19:48:52 +0200 (CEST)\n" +
                "Received: from ns1.median.ru (ns1.median.ru [80.92.10.252] (may be forged))\n" +
                "\tby ncc0.median.ru (8.13.8/8.13.8) with ESMTP id m8EIbT8j016016;\n" +
                "\tSun, 14 Sep 2008 22:37:29 +0400\n" +
                "Received: from median.ru (pppz5.median.ru [80.92.31.5])\n" +
                "\tby ns1.median.ru (8.13.8/8.13.8) with SMTP id m8EIb4im019995;\n" +
                "\tSun, 14 Sep 2008 22:37:07 +0400\n" +
                "Message-ID: <606165A1.211E6652@median.ru>\n" +
                "Date: Sun, 14 Sep 2008 11:23:09 -0800\n" +
                "From: \"Das Erste Kasino\" <xqnowoyl@median.ru>\n" +
                "To: <herbzgwt@ifi.acme.org>\n" +
                "Cc: <adrian@ifi.acme.org>, <herbfelf@ifi.acme.org>,\n" +
                "\t<gruberwolfgang@ifi.acme.org>, <herbjfxe@ifi.acme.org>,\n" +
                "\t<herbctas@ifi.acme.org>, <redefinitions@ifi.acme.org>,\n" +
                "\t<assi@ifi.acme.org>\n" +
                "Subject: ***SPAM*** =?Windows-1252?Q?Herzlichen_Gl=FCckwunsch=2C_Sie_haben_Casinochips_im_Wer?=\n" +
                "\t=?Windows-1252?Q?t_von_300_Euro_gewonnen_!__rphuz?=\n" +
                "MIME-Version: 1.0\n" +
                "Content-Type: text/html;\n" +
                "\tformat=flowed;\n" +
                "\tcharset=\"Windows-1252\";\n" +
                "\treply-type=original\n" +
                "Content-Transfer-Encoding: 7bit\n" +
                "X-Virus-Scanned: by amavisd-new-2.2.1 (20041222) (Debian) at isys.acme.org\n" +
                "X-Virus-Scanned: ClamAV using ClamSMTP\n" +
                "X-Spam-Prev-Subject: ***SPAM*** =?Windows-1252?Q?Herzlichen_Gl=FCckwunsch=2C_Sie_haben_Casinochips_im_Wer?=\n" +
                "X-Virus-Scanned: amavisd-new at mailman.dept.acme.org\n" +
                "\n" +
                "\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<meta http-equiv=Content-Type content=\"text/html; charset=windows-1252\">\n" +
                "\n" +
                "<style>\n" +
                "<!--\n" +
                " /* Style Definitions */\n" +
                " p.MsoNormal, li.MsoNormal, div.MsoNormal\n" +
                "\t{mso-style-parent:\"\";\n" +
                "\tmargin:0cm;\n" +
                "\tmargin-bottom:.0001pt;\n" +
                "\tmso-pagination:widow-orphan;\n" +
                "\tfont-size:12.0pt;\n" +
                "\tfont-family:\"Times New Roman\";\n" +
                "\tmso-fareast-font-family:\"Times New Roman\";}\n" +
                "a:link, span.MsoHyperlink\n" +
                "\t{color:blue;\n" +
                "\ttext-decoration:underline;\n" +
                "\ttext-underline:single;}\n" +
                "a:visited, span.MsoHyperlinkFollowed\n" +
                "\t{color:purple;\n" +
                "\ttext-decoration:underline;\n" +
                "\ttext-underline:single;}\n" +
                "@page Section1\n" +
                "\t{size:595.3pt 841.9pt;\n" +
                "\tmargin:2.0cm 42.5pt 2.0cm 3.0cm;\n" +
                "\tmso-header-margin:35.4pt;\n" +
                "\tmso-footer-margin:35.4pt;\n" +
                "\tmso-paper-source:0;}\n" +
                "div.Section1\n" +
                "\t{page:Section1;}\n" +
                "-->\n" +
                "</style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body lang=DE link=blue vlink=purple style='tab-interval:35.4pt'>\n" +
                "\n" +
                "<div class=Section1>\n" +
                "\n" +
                "<p class=MsoNormal>\n" +
                "<span lang=DE style='font-size:17.0pt;mso-ansi-language:DE'>\n" +
                "Spielen und &euro;300 JETZT erhalten, hier klicken: \n" +
                "<o:p></o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal><span lang=DE style='mso-ansi-language:DE'>\n" +
                "\n" +
                "<o:p>&nbsp;</o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal>\n" +
                "<span lang=DE style='font-size:16.0pt;mso-ansi-language:DE'>\n" +
                "<a href=\"http://www.firstcasinolite.com/de/\">\n" +
                "http://www.firstcasinolite.com/de/</a>\n" +
                "<o:p></o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal><span lang=DE style='mso-ansi-language:DE'>\n" +
                "<hr><o:p>&nbsp;</o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal><span lang=DE style='mso-ansi-language:DE'>\n" +
                "<o:p>&nbsp;</o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal><span lang=DE style='mso-ansi-language:DE'>\n" +
                "<o:p>&nbsp;</o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal><span lang=DE style='font-size:7.0pt;mso-ansi-language:DE'>\n" +
                "Iqobynp vora zu zrkamag \n" +
                "<o:p></o:p></span></p>\n" +
                "\n" +
                "<p class=MsoNormal><span lang=DE style='font-size:7.0pt;mso-ansi-language:DE'>\n" +
                "Bubyzasyly ueucoosyb fmzonowax :)\n" +
                "<o:p></o:p></span></p>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>\n" +
                "");
        messages.add(
                "Return-Path: <Glynn_Johnson@ajg.com>\n" +
                "X-Original-To: roadrunner@dept.acme.org\n" +
                "Delivered-To: roadrunner@dept.acme.org\n" +
                "Received: from mailman.dept.acme.org (mailman-dept.acme.org [10.0.0.139])\n" +
                "\tby mail.dept.acme.org (Postfix) with ESMTP id 3C77A7EF\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 15 Sep 2008 08:46:50 +0200 (CEST)\n" +
                "Received: from localhost (localhost [127.0.0.1])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 576892DE6\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 15 Sep 2008 08:46:50 +0200 (CEST)\n" +
                "Received: from mailman.dept.acme.org ([127.0.0.1])\n" +
                "\tby localhost (mailman-dept.acme.org [127.0.0.1]) (amavisd-new, port 10024)\n" +
                "\twith ESMTP id 21126-06 for <roadrunner@dept.acme.org>;\n" +
                "\tMon, 15 Sep 2008 08:46:50 +0200 (CEST)\n" +
                "Received: from mx.acme.org (mx.acme.org [143.205.180.45])\n" +
                "\tby mailman.dept.acme.org (Postfix) with ESMTP id 2572C803\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 15 Sep 2008 08:46:49 +0200 (CEST)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id B521787810\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 15 Sep 2008 08:46:42 +0200 (CEST)\n" +
                "X-Spam-Flag: YES\n" +
                "X-Spam-Checker-Version: SpamAssassin 3.2.3 (2007-08-08) on mx.acme.org\n" +
                "X-Spam-Level: *************\n" +
                "X-Spam-Status: Yes, score=13.2 required=4.5 tests=ADVANCE_FEE_2,ADVANCE_FEE_3,\n" +
                "\tADVANCE_FEE_4,BAYES_50,DCC_CHECK,MILLION_USD,SARE_FRAUD_X3,SARE_FRAUD_X4,\n" +
                "\tSARE_FRAUD_X5 autolearn=spam version=3.2.3\n" +
                "X-Spam-Report: \n" +
                "\t*  1.5 MILLION_USD BODY: Talks about millions of dollars\n" +
                "\t*  0.0 BAYES_50 BODY: Bayesian spam probability is 40 to 60%\n" +
                "\t*      [score: 0.5000]\n" +
                "\t*  3.4 DCC_CHECK Listed in DCC (http://rhyolite.com/anti-spam/dcc/)\n" +
                "\t*  1.2 ADVANCE_FEE_2 Appears to be advance fee fraud (Nigerian 419)\n" +
                "\t*  1.7 SARE_FRAUD_X5 Matches 5+ phrases commonly used in fraud spam\n" +
                "\t*  1.4 ADVANCE_FEE_3 Appears to be advance fee fraud (Nigerian 419)\n" +
                "\t*  1.7 SARE_FRAUD_X3 Matches 3+ phrases commonly used in fraud spam\n" +
                "\t*  1.7 SARE_FRAUD_X4 Matches 4+ phrases commonly used in fraud spam\n" +
                "\t*  0.6 ADVANCE_FEE_4 Appears to be advance fee fraud (Nigerian 419)\n" +
                "Received: from mx.acme.org (localhost [127.0.0.1])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 913D2154001\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 15 Sep 2008 08:46:42 +0200 (CEST)\n" +
                "X-Greylist: Passed host: 12.26.206.74\n" +
                "Received: from mail2.gallagheronline.com (mail2.gallagheronline.com [12.26.206.74])\n" +
                "\tby mx.acme.org (Postfix) with ESMTP id 3A3DF87810\n" +
                "\tfor <roadrunner@dept.acme.org>; Mon, 15 Sep 2008 08:46:38 +0200 (CEST)\n" +
                "Received: from unknown (HELO 10.2.14.200) ([10.2.14.200])\n" +
                "  by itascamta.gallagheronline.com with ESMTP; 15 Sep 2008 01:46:35 -0500\n" +
                "Received: from 192.168.230.158 ([192.168.230.158])\n" +
                "          by 10.2.14.200 (PostX Enterprise 6.2.5 SMTP Adapter) with SMTP ID 94\n" +
                "          for <roadrunner@dept.acme.org>;\n" +
                "          Mon, 15 Sep 2008 01:46:02 -0500 (CDT)\n" +
                "X-IronPort-PostX: T\n" +
                "Received: from unknown (HELO mail1.gallagheronline.com) ([10.2.0.157])\n" +
                "  by itascamta.gallagheronline.com with ESMTP; 15 Sep 2008 01:46:27 -0500\n" +
                "Importance: Normal\n" +
                "X-Priority: 3 (Normal)\n" +
                "In-Reply-To: \n" +
                "References: \n" +
                "Subject: ***SPAM*** Mrs Susan fernando\n" +
                "MIME-Version: 1.0\n" +
                "From: Glynn_Johnson@ajg.com\n" +
                "X-MIMETrack: MIME-CD by HTTP Server on HC03/AJG(Release 7.0.3 HF768|July 17, 2008) at 09/15/2008\n" +
                " 01:45:59 AM,\n" +
                "\tMIME-CD complete at 09/15/2008 01:45:59 AM,\n" +
                "\tSerialize by Router on AJGSMTP01/AJG(Release 7.0.3|September 26, 2007) at\n" +
                " 09/15/2008 01:46:27 AM\n" +
                "Date: Mon, 15 Sep 2008 01:45:59 -0500\n" +
                "X-Mailer: Lotus Domino Web Server Release 7.0.3 HF768 July 17, 2008            \n" +
                "Message-ID: <8552151.1221461162691.JavaMail.SYSTEM@crpsecmail2>\n" +
                "Content-type: text/plain; charset=US-ASCII\n" +
                "X-Notes-Item: roadrunner@dept.acme.org; name=AltBlindCopyTo\n" +
                "X-PostX-Message-ID: ce6a8540c33ac2640a020ec82fc51af2@crpsecmail2\n" +
                "Content-Transfer-Encoding: 7bit\n" +
                "To: undisclosed-recipients:;\n" +
                "X-Virus-Scanned: ClamAV using ClamSMTP\n" +
                "X-Spam-Prev-Subject: Mrs Susan fernando\n" +
                "X-Virus-Scanned: amavisd-new at mailman.dept.acme.org\n" +
                "\n" +
                "\n" +
                "reply to my private email ;sazo1919@aim.com\n" +
                "\n" +
                "\n" +
                "Mrs Susan fernando\n" +
                "\n" +
                "ENDEAVOUR TO USED IT FOR THE CHILDREN OF GOD.\n" +
                "I am the above named person from Kuwait. I am married to Dr SAZON FERNANDO\n" +
                "who worked with Kuwait embassy in Ivory Coast for nine years before he\n" +
                "died in the year 2005.We were married for eleven years without a child. He\n" +
                "died after a brief illness that lasted for only four days. Before his\n" +
                "death we were both born again Christians.Since his death I decided not to\n" +
                "re-marry or get a child outside my matrimonial home which the Bible is\n" +
                "against.When my late husband was alive he deposited the sum of 18Million\n" +
                "Dollars (eighteen Million United State Dollars) with one finance/security\n" +
                "company in Amsterderm-Netherlands. Presently\n" +
                "This money is still with the\n" +
                "Security Company. Recently\n" +
                "my Doctor told me that I would not last for\n" +
                "the next three months due to cancer problem. Though what disturbs me most\n" +
                "is my stroke sickness. Having known my condition I decided to donate this\n" +
                "Fund to church or better still a christian individual that will utilize\n" +
                "this money the way I am going to instruct here in. I want a church that\n" +
                "will use this funds to fund churches\n" +
                "orphanages and widows propagating the\n" +
                "word of God and to ensure that the house of God is maintained. The Bible\n" +
                "made us to understand that Blessed is the hand that giveth. I took this\n" +
                "decision because I don't have any child that will inherit this money and\n" +
                "my husband relatives are not Christians and I don't want my husband's\n" +
                "hard\n" +
                "earned money to be misused by unbelievers. I don't want a situation where\n" +
                "this money will be used in an ungodly manner. Hence the reason for taking\n" +
                "this bold decision. I am not afraid of death hence I know where I am\n" +
                "going. I know that I am going to be in the bosom of the Lord. Exodus 14 VS\n" +
                "14 says that the lord will fight my case and I shall hold my peace. I\n" +
                "don't need any telephone communication in this regard because of my health\n" +
                "and because of the presence of my husband's relatives around me always. I\n" +
                "don't want them to know about this development. With God all things are\n" +
                "possible. As soon as I receive your reply I shall give you the contact of\n" +
                "the Finance/Security Company in Amsterderm-Netherlands. I will also issue\n" +
                "you a letter of authority that will prove you as the original- beneficiary\n" +
                "of this Funds. I want you and the church to always pray for me because the\n" +
                "lord is my shephard. My happiness is that I lived a life of a worthy\n" +
                "Christian. Whoever that wants to serve the Lord must serve him in spirit\n" +
                "and truth. Please always be prayerful all through your life. Any delay in\n" +
                "your reply will give me room in sourcing for a church or christian\n" +
                "individual for this same purpose. Please assure me that you will act\n" +
                "accordingly as I stated herein. Hoping to hearing from you. I have set\n" +
                "aside 20% for you and for your time and 10% for any enpense if there is\n" +
                "any . Remain blessed in the name of the Lord. Yours in Christ\n" +
                "Mrs. susan fernando\n" +
                "Note: reply email to sazo1919@aim.com\n" +
                "");
    }
}
