package eu.yeger.kotlin.javafx

import javafx.scene.control.Button
import javafx.scene.control.Label
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.testfx.assertions.api.Assertions.assertThat
import org.testfx.framework.junit5.ApplicationTest

class NodeTests : ApplicationTest() {

    @Test
    fun testMultipleInstanceCreations() {
        val singleButtonFragment = button("IncludedButton")
        val container = vBox {
            child { singleButtonFragment }
        }.instance()
        val anotherContainer = vBox {
            child { singleButtonFragment }
        }.instance()
        assertThat(container.children[0] as Button).hasText("IncludedButton")
        assertThat(anotherContainer.children[0] as Button).hasText("IncludedButton")
    }

    @Nested
    inner class GridPaneTests {

        @Test
        fun testAddingChildrenWithCoordinates() {
            val gridPane = gridPane {
                child(0, 0) {
                    label("First")
                }
                child(1, 1) {
                    button("Second")
                }
            }.instance()
            assertThat(gridPane).hasExactlyNumChildren(2)
            assertThat(gridPane.children[0] as Label).hasText("First")
            assertThat(gridPane.children[1] as Button).hasText("Second")
        }
    }
}