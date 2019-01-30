package br.com.jheimesilveira.exemple

import br.com.jheimesilveira.js.tableview.model.Cell
import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row
import java.text.NumberFormat
import kotlin.random.Random

object GenerateMock {

    val rand = java.util.Random()

    /**
     * Prepares dummy data
     */
    fun prepareData(rows: Int, column: Int = 0): ArrayList<Row> {
        val array = ArrayList<Row>()

        for (i in 0 until rows) {
            val skRow = Row(getPrepareColumn(column))
            array.add(skRow)
        }

        return array
    }

    private fun getPrepareColumn(column: Int = 0): ArrayList<Cell> {
        val array = ArrayList<Cell>()

        var skCell: Cell?
        if (column >= 0) {
            skCell = Cell(data = arrayName[rand.nextInt(maxValue)])
            array.add(skCell)
        }

        if (column >= 1) {
            skCell = Cell(data = GeradorCPF().geraCPF())
            array.add(skCell)
        }

        if (column >= 2) {
            skCell = Cell(data = generateValue(100000))
            array.add(skCell)
        }

        return array
    }

    fun getPrepareColumnHeader(column: Int = 0): ArrayList<ColumnHeader> {
        val array = ArrayList<ColumnHeader>()
        var cellColumn: ColumnHeader?

        if (column >= 0) {
            cellColumn = ColumnHeader(data = "Nomes")
            array.add(cellColumn)
        }

        if (column >= 1) {
            cellColumn = ColumnHeader(data = "CPF")
            array.add(cellColumn)
        }

        if (column >= 2) {
            cellColumn = ColumnHeader(data = "Salário")
            array.add(cellColumn)
        }

        return array
    }

    private val maxValue = 100
    private val arrayName = arrayOf(
            "Abílio Valiente",
            "Adriano Oiticica",
            "Adérito Quintão",
            "Aguinaldo Peña",
            "Alberta Assumpção",
            "Albino Negrão",
            "Alvito Reis",
            "Alzira Leiria",
            "Ana Mota",
            "Anita Miguel",
            "Antonieta Lousã",
            "Apuã Lóio",
            "Balduíno Noite",
            "Belchior Negrão",
            "Benedito Casqueira",
            "Bernardo Tristán",
            "Brás Machado",
            "Carolina Abreu",
            "Celina Colaço",
            "Clara Palha",
            "Clarisse Vilanova",
            "Conrado Calheiros",
            "Cosperranho Paixão",
            "Cosperranho Rodríguez",
            "Cristiano Bragança",
            "Cândido Rosa",
            "Deolindo Chávez",
            "Derli Franca",
            "Donato Jordán",
            "Emanuel Quaresma",
            "Eugénio Sousa do Prado",
            "Eusébio Camarinho",
            "Fernanda Bairros",
            "Garibaldo Aires",
            "Gaudêncio Cavalheiro",
            "Gláuber Campos",
            "Gláucio Camacho",
            "Godinho ou Godim Imbassaí",
            "Gualdim Laureano",
            "Gualdim Lopes",
            "Hedviges Linares",
            "Heitor Imbassaí",
            "Helena Landim",
            "Hermenegildo Estrella",
            "Hugo Pêssego",
            "Ilduara Cortês",
            "Ilídio Beiriz",
            "Isadora Franco",
            "Ivete Alvarenga",
            "Jacira Ginjeira",
            "Jonas Thamily",
            "Jordana Vaz",
            "Jorge Pena",
            "Jorgina Almada",
            "Josefa Mirandela",
            "Judite Tabalipa",
            "Justino Antas",
            "Laurinda Thomé",
            "Leopoldo Rabello",
            "Liedson Sardina",
            "Liliana Cisneros",
            "Lígia Verissimo",
            "Lúcio Saltão",
            "Martim Ferro",
            "Martinho Rangel",
            "Mem Neto",
            "Moacir Nascimento",
            "Nestor Bautista",
            "Neusa Oleiro",
            "Ofélia Castillo",
            "Olívia Ipiranga",
            "Palmira Rufino",
            "Paraguaçu Brasil",
            "Penélope Varanda",
            "Quintilien Borja",
            "Ramão Matoso",
            "Regina Chávez",
            "Rosália Vilar",
            "Rubim Faia",
            "Ruca Barboza",
            "Rui Chousa",
            "Salvina Beça",
            "Serafim Rosmaninho",
            "Silvana Sousa de Arronches",
            "Sónia Piratininga",
            "Tomás Taveira",
            "Tânia Rijo",
            "Ubaldo Leme",
            "Ubirajara Alcantara",
            "Ubiratã Pastana",
            "Valentim Tamoio",
            "Vera Morales",
            "Viviana Suárez",
            "Xavier Cascais",
            "Zara Peres",
            "Zara Sousa do Prado",
            "Ângelo Pêssego",
            "Élvio Negrão",
            "Érico Mieiro",
            "Úrsula Olivares")

    fun generateValue(maxValue: Int): String {
        val rand = Random(maxValue)
        val formatter = NumberFormat.getCurrencyInstance()
        val moneyString = formatter.format(rand.nextDouble(maxValue.toDouble()))

        return moneyString
    }
}