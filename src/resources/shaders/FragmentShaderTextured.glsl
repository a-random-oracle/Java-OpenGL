#version 150 core

uniform sampler2D texture_diffuse;

in vec4 pass_Colour;
in vec2 pass_TexturePos;

out vec4 out_Colour;

void main(void) {
	out_Colour = pass_Colour;
	out_Colour = texture(texture_diffuse, pass_TexturePos);
}