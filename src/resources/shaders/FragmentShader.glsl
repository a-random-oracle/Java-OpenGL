#version 150 core

in vec4 pass_Colour;

out vec4 out_Colour;

void main(void) {
	out_Colour = pass_Colour;
}