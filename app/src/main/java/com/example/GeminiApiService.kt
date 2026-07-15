package com.example

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@JsonClass(generateAdapter = true)
data class Part(
    val text: String? = null
)

@JsonClass(generateAdapter = true)
data class Content(
    val parts: List<Part>
)

@JsonClass(generateAdapter = true)
data class GenerateContentRequest(
    val contents: List<Content>
)

@JsonClass(generateAdapter = true)
data class PartResponse(
    val text: String? = null
)

@JsonClass(generateAdapter = true)
data class ContentResponse(
    val parts: List<PartResponse>? = null
)

@JsonClass(generateAdapter = true)
data class Candidate(
    val content: ContentResponse? = null
)

@JsonClass(generateAdapter = true)
data class GenerateContentResponse(
    val candidates: List<Candidate>? = null
)

interface GeminiApiService {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GenerateContentRequest
    ): GenerateContentResponse
}

object GeminiRetrofitClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val service: GeminiApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        retrofit.create(GeminiApiService::class.java)
    }
}

suspend fun generatePromptFromDesignNeeds(userNeeds: String): String {
    val apiKey = BuildConfig.GEMINI_API_KEY
    if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
        return "Error: Gemini API key is missing. Please set GEMINI_API_KEY in the Secrets panel."
    }

    val systemPrompt = "You are an expert AI Design Assistant. The user will describe their brand or design needs. " +
            "Your job is to generate a highly detailed, evocative, descriptive visual prompt suitable for an image generation model (like Imagen, Midjourney, or Stable Diffusion). " +
            "The generated prompt should focus on visual composition, lighting, style, layout, elements, color palettes, and overall mood based on the user's input. " +
            "Output ONLY the optimized prompt itself, do not include any intro, outro, preamble, or formatting (like quotes or markdown formatting)."

    val fullPrompt = "$systemPrompt\n\nUser's Design Needs: $userNeeds\n\nOptimized Image Prompt:"

    val request = GenerateContentRequest(
        contents = listOf(
            Content(
                parts = listOf(
                    Part(text = fullPrompt)
                )
            )
        )
    )

    return try {
        val response = GeminiRetrofitClient.service.generateContent(apiKey, request)
        val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
        if (!text.isNullOrBlank()) {
            text.trim()
        } else {
            "Error: No content returned from Gemini API."
        }
    } catch (e: Exception) {
        "Error calling Gemini API: ${e.message}"
    }
}

suspend fun generateLogoPrompt(brandName: String, industry: String, styleNotes: String): String {
    val apiKey = BuildConfig.GEMINI_API_KEY
    if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
        return "Error: Gemini API key is missing. Please set GEMINI_API_KEY in the Secrets panel."
    }

    val systemPrompt = "You are an expert AI Branding and Logo Designer. The user will provide their brand name, industry, and some style preferences. " +
            "Your job is to generate a highly detailed, evocative, descriptive visual prompt suitable for an image generation model (like Imagen, Midjourney, or Stable Diffusion) to generate a professional logo. " +
            "The generated prompt should describe the logo composition, central icon or symbol, layout, background (e.g. flat, vector, white background), style (e.g. modern, minimalist, geometric), typography details, color palette, and visual mood. " +
            "Ensure the brand name is incorporated elegantly if appropriate, or focus on a symbol. " +
            "Output ONLY the optimized prompt itself, do not include any intro, outro, preamble, or formatting (like quotes or markdown formatting)."

    val fullPrompt = "$systemPrompt\n\nBrand Name: $brandName\nIndustry: $industry\nStyle Preference/Notes: $styleNotes\n\nOptimized Logo Image Prompt:"

    val request = GenerateContentRequest(
        contents = listOf(
            Content(
                parts = listOf(
                    Part(text = fullPrompt)
                )
            )
        )
    )

    return try {
        val response = GeminiRetrofitClient.service.generateContent(apiKey, request)
        val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
        if (!text.isNullOrBlank()) {
            text.trim()
        } else {
            "Error: No content returned from Gemini API."
        }
    } catch (e: Exception) {
        "Error calling Gemini API: ${e.message}"
    }
}

suspend fun generateDesignPrompt(
    projectDescription: String,
    stylePreference: String,
    designType: String
): String {
    val apiKey = BuildConfig.GEMINI_API_KEY
    if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
        return "Error: Gemini API key is missing. Please set GEMINI_API_KEY in the Secrets panel."
    }

    val systemPrompt = "You are an expert AI Brand Identity & Graphic Designer. " +
            "The user will describe their project or brand, and specify their design style preference and target design type (Logo or Poster). " +
            "Your job is to generate a highly detailed, evocative, descriptive visual prompt suitable for an image generation model (such as Stable Diffusion or Midjourney) to generate a premium-quality $designType. " +
            "If the type is a Logo, the prompt should describe a centered vector logo on a solid, clean, flat background (e.g. white or dark blue background), emphasizing iconography, clean shapes, vector paths, flat colors, or subtle modern gradients, and clear geometry. Avoid photographic background elements. " +
            "If the type is a Poster, the prompt should describe a complete illustrative composition, artistic rendering style, beautiful framing, dramatic lighting, rich textures, and expressive typographic layout hints. " +
            "Incorporate elements of the specified style: '$stylePreference'. " +
            "Output ONLY the optimized image prompt itself, do not include any intro, outro, preamble, or markdown code-blocks."

    val fullPrompt = "$systemPrompt\n\nProject/Brand Description: $projectDescription\nStyle Preference: $stylePreference\nDesign Type: $designType\n\nOptimized Image Prompt:"

    val request = GenerateContentRequest(
        contents = listOf(
            Content(
                parts = listOf(
                    Part(text = fullPrompt)
                )
            )
        )
    )

    return try {
        val response = GeminiRetrofitClient.service.generateContent(apiKey, request)
        val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
        if (!text.isNullOrBlank()) {
            text.trim()
        } else {
            "Error: No content returned from Gemini API."
        }
    } catch (e: Exception) {
        "Error calling Gemini API: ${e.message}"
    }
}


